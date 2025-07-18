package ml.docilealligator.infinityforreddit.adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.Markwon;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.core.MarkwonTheme;
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin;
import io.noties.markwon.inlineparser.BangInlineProcessor;
import io.noties.markwon.inlineparser.HtmlInlineProcessor;
import io.noties.markwon.inlineparser.MarkwonInlineParserPlugin;
import io.noties.markwon.linkify.LinkifyPlugin;
import io.noties.markwon.movement.MovementMethodPlugin;
import ml.docilealligator.infinityforreddit.NetworkState;
import ml.docilealligator.infinityforreddit.R;
import ml.docilealligator.infinityforreddit.activities.BaseActivity;
import ml.docilealligator.infinityforreddit.activities.LinkResolverActivity;
import ml.docilealligator.infinityforreddit.activities.ViewPrivateMessagesActivity;
import ml.docilealligator.infinityforreddit.activities.ViewUserDetailActivity;
import ml.docilealligator.infinityforreddit.customtheme.CustomThemeWrapper;
import ml.docilealligator.infinityforreddit.databinding.ItemFooterErrorBinding;
import ml.docilealligator.infinityforreddit.databinding.ItemFooterLoadingBinding;
import ml.docilealligator.infinityforreddit.databinding.ItemMessageBinding;
import ml.docilealligator.infinityforreddit.events.ChangeInboxCountEvent;
import ml.docilealligator.infinityforreddit.markdown.RedditHeadingPlugin;
import ml.docilealligator.infinityforreddit.markdown.SpoilerAwareMovementMethod;
import ml.docilealligator.infinityforreddit.markdown.SpoilerParserPlugin;
import ml.docilealligator.infinityforreddit.markdown.SuperscriptPlugin;
import ml.docilealligator.infinityforreddit.message.FetchMessage;
import ml.docilealligator.infinityforreddit.message.Message;
import ml.docilealligator.infinityforreddit.message.ReadMessage;
import retrofit2.Retrofit;

public class MessageRecyclerViewAdapter extends PagedListAdapter<Message, RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_ERROR = 1;
    private static final int VIEW_TYPE_LOADING = 2;
    private static final DiffUtil.ItemCallback<Message> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Message message, @NonNull Message t1) {
            return message.getId().equals(t1.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message message, @NonNull Message t1) {
            return message.getBody().equals(t1.getBody());
        }
    };
    private final BaseActivity mActivity;
    private final Retrofit mOauthRetrofit;
    private final Markwon mMarkwon;
    private final String mAccessToken;
    private final int mMessageType;
    private NetworkState networkState;
    private final RetryLoadingMoreCallback mRetryLoadingMoreCallback;
    private final int mColorAccent;
    private final int mMessageBackgroundColor;
    private final int mUsernameColor;
    private final int mPrimaryTextColor;
    private final int mSecondaryTextColor;
    private final int mUnreadMessageBackgroundColor;
    private final int mColorPrimaryLightTheme;
    private final int mButtonTextColor;
    private boolean markAllMessagesAsRead = false;

    public MessageRecyclerViewAdapter(BaseActivity activity, Retrofit oauthRetrofit,
                                      CustomThemeWrapper customThemeWrapper,
                                      String accessToken, String where,
                                      RetryLoadingMoreCallback retryLoadingMoreCallback) {
        super(DIFF_CALLBACK);
        mActivity = activity;
        mOauthRetrofit = oauthRetrofit;
        mRetryLoadingMoreCallback = retryLoadingMoreCallback;

        mColorAccent = customThemeWrapper.getColorAccent();
        mMessageBackgroundColor = customThemeWrapper.getCardViewBackgroundColor();
        mUsernameColor = customThemeWrapper.getUsername();
        mPrimaryTextColor = customThemeWrapper.getPrimaryTextColor();
        mSecondaryTextColor = customThemeWrapper.getSecondaryTextColor();
        int spoilerBackgroundColor = mSecondaryTextColor | 0xFF000000;
        mUnreadMessageBackgroundColor = customThemeWrapper.getUnreadMessageBackgroundColor();
        mColorPrimaryLightTheme = customThemeWrapper.getColorPrimaryLightTheme();
        mButtonTextColor = customThemeWrapper.getButtonTextColor();

        // todo:https://github.com/Docile-Alligator/Infinity-For-Reddit/issues/1027
        //  add tables support and replace with MarkdownUtils#commonPostMarkwonBuilder
        mMarkwon = Markwon.builder(mActivity)
                .usePlugin(MarkwonInlineParserPlugin.create(plugin -> {
                    plugin.excludeInlineProcessor(HtmlInlineProcessor.class);
                    plugin.excludeInlineProcessor(BangInlineProcessor.class);
                }))
                .usePlugin(new AbstractMarkwonPlugin() {
                    @Override
                    public void configureConfiguration(@NonNull MarkwonConfiguration.Builder builder) {
                        builder.linkResolver((view, link) -> {
                            Intent intent = new Intent(mActivity, LinkResolverActivity.class);
                            Uri uri = Uri.parse(link);
                            intent.setData(uri);
                            mActivity.startActivity(intent);
                        });
                    }

                    @Override
                    public void configureTheme(@NonNull MarkwonTheme.Builder builder) {
                        builder.linkColor(customThemeWrapper.getLinkColor());
                    }
                })
                .usePlugin(SuperscriptPlugin.create())
                .usePlugin(SpoilerParserPlugin.create(mSecondaryTextColor, spoilerBackgroundColor))
                .usePlugin(RedditHeadingPlugin.create())
                .usePlugin(StrikethroughPlugin.create())
                .usePlugin(MovementMethodPlugin.create(new SpoilerAwareMovementMethod()))
                .usePlugin(LinkifyPlugin.create(Linkify.WEB_URLS))
                .build();
        mAccessToken = accessToken;
        if (where.equals(FetchMessage.WHERE_MESSAGES)) {
            mMessageType = FetchMessage.MESSAGE_TYPE_PRIVATE_MESSAGE;
        } else {
            mMessageType = FetchMessage.MESSAGE_TYPE_INBOX;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DATA) {
            return new DataViewHolder(ItemMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else if (viewType == VIEW_TYPE_ERROR) {
            return new ErrorViewHolder(ItemFooterErrorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new LoadingViewHolder(ItemFooterLoadingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DataViewHolder) {
            Message message = getItem(holder.getBindingAdapterPosition());
            if (message != null) {
                ArrayList<Message> replies = message.getReplies();
                Message displayedMessage;
                if (replies != null && !replies.isEmpty() && replies.get(replies.size() - 1) != null) {
                    displayedMessage = replies.get(replies.size() - 1);
                } else {
                    displayedMessage = message;
                }
                if (message.isNew()) {
                    if (markAllMessagesAsRead) {
                        message.setNew(false);
                    } else {
                        holder.itemView.setBackgroundColor(
                                mUnreadMessageBackgroundColor);
                    }
                }

                if (message.wasComment()) {
                    ((DataViewHolder) holder).binding.titleTextViewItemMessage.setText(message.getTitle());
                } else {
                    ((DataViewHolder) holder).binding.titleTextViewItemMessage.setVisibility(View.GONE);
                }

                ((DataViewHolder) holder).binding.authorTextViewItemMessage.setText(displayedMessage.getAuthor());
                String subject = displayedMessage.getSubject().substring(0, 1).toUpperCase() + displayedMessage.getSubject().substring(1);
                ((DataViewHolder) holder).binding.subjectTextViewItemMessage.setText(subject);
                mMarkwon.setMarkdown(((DataViewHolder) holder).binding.contentCustomMarkwonViewItemMessage, displayedMessage.getBody());

                holder.itemView.setOnClickListener(view -> {
                    if (mMessageType == FetchMessage.MESSAGE_TYPE_INBOX
                            && message.getContext() != null && !message.getContext().equals("")) {
                        Uri uri = Uri.parse(message.getContext());
                        Intent intent = new Intent(mActivity, LinkResolverActivity.class);
                        intent.setData(uri);
                        mActivity.startActivity(intent);
                    } else if (mMessageType == FetchMessage.MESSAGE_TYPE_PRIVATE_MESSAGE) {
                        Intent intent = new Intent(mActivity, ViewPrivateMessagesActivity.class);
                        intent.putExtra(ViewPrivateMessagesActivity.EXTRA_PRIVATE_MESSAGE_INDEX, holder.getBindingAdapterPosition());
                        intent.putExtra(ViewPrivateMessagesActivity.EXTRA_MESSAGE_POSITION, holder.getBindingAdapterPosition());
                        mActivity.startActivity(intent);
                    }

                    if (displayedMessage.isNew()) {
                        holder.itemView.setBackgroundColor(mMessageBackgroundColor);
                        message.setNew(false);

                        ReadMessage.readMessage(mOauthRetrofit, mAccessToken, message.getFullname(),
                                new ReadMessage.ReadMessageListener() {
                                    @Override
                                    public void readSuccess() {
                                        EventBus.getDefault().post(new ChangeInboxCountEvent(-1));
                                    }

                                    @Override
                                    public void readFailed() {
                                        message.setNew(true);
                                        holder.itemView.setBackgroundColor(mUnreadMessageBackgroundColor);
                                    }
                                });
                    }
                });

                ((DataViewHolder) holder).binding.authorTextViewItemMessage.setOnClickListener(view -> {
                    if (message.isAuthorDeleted()) {
                        return;
                    }
                    Intent intent = new Intent(mActivity, ViewUserDetailActivity.class);
                    intent.putExtra(ViewUserDetailActivity.EXTRA_USER_NAME_KEY, message.getAuthor());
                    mActivity.startActivity(intent);
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Reached at the end
        if (hasExtraRow() && position == getItemCount() - 1) {
            if (networkState.getStatus() == NetworkState.Status.LOADING) {
                return VIEW_TYPE_LOADING;
            } else {
                return VIEW_TYPE_ERROR;
            }
        } else {
            return VIEW_TYPE_DATA;
        }
    }

    @Override
    public int getItemCount() {
        if (hasExtraRow()) {
            return super.getItemCount() + 1;
        }
        return super.getItemCount();
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof DataViewHolder) {
            ((DataViewHolder) holder).itemView.setBackgroundColor(mMessageBackgroundColor);
            ((DataViewHolder) holder).binding.titleTextViewItemMessage.setVisibility(View.VISIBLE);
        }
    }

    private boolean hasExtraRow() {
        return networkState != null && networkState.getStatus() != NetworkState.Status.SUCCESS;
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(super.getItemCount());
            } else {
                notifyItemInserted(super.getItemCount());
            }
        } else if (newExtraRow && !previousState.equals(newNetworkState)) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    public void updateMessageReply(Message newReply, int position) {
        if (position >= 0 && position < super.getItemCount()) {
            Message message = getItem(position);
            if (message != null) {
                notifyItemChanged(position);
            }
        }
    }

    public void setMarkAllMessagesAsRead(boolean markAllMessagesAsRead) {
        this.markAllMessagesAsRead = markAllMessagesAsRead;
    }

    public interface RetryLoadingMoreCallback {
        void retryLoadingMore();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        ItemMessageBinding binding;

        DataViewHolder(@NonNull ItemMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            if (mActivity.typeface != null) {
                binding.authorTextViewItemMessage.setTypeface(mActivity.typeface);
                binding.subjectTextViewItemMessage.setTypeface(mActivity.typeface);
                binding.titleTextViewItemMessage.setTypeface(mActivity.titleTypeface);
                binding.contentCustomMarkwonViewItemMessage.setTypeface(mActivity.contentTypeface);
            }
            itemView.setBackgroundColor(mMessageBackgroundColor);
            binding.authorTextViewItemMessage.setTextColor(mUsernameColor);
            binding.subjectTextViewItemMessage.setTextColor(mPrimaryTextColor);
            binding.titleTextViewItemMessage.setTextColor(mPrimaryTextColor);
            binding.contentCustomMarkwonViewItemMessage.setTextColor(mSecondaryTextColor);

            binding.contentCustomMarkwonViewItemMessage.setMovementMethod(LinkMovementMethod.getInstance());

            binding.contentCustomMarkwonViewItemMessage.setOnClickListener(view -> {
                if (binding.contentCustomMarkwonViewItemMessage.getSelectionStart() == -1 && binding.contentCustomMarkwonViewItemMessage.getSelectionEnd() == -1) {
                    itemView.performClick();
                }
            });
        }
    }

    class ErrorViewHolder extends RecyclerView.ViewHolder {
        ItemFooterErrorBinding binding;

        ErrorViewHolder(@NonNull ItemFooterErrorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            if (mActivity.typeface != null) {
                binding.errorTextViewItemFooterError.setTypeface(mActivity.typeface);
                binding.retryButtonItemFooterError.setTypeface(mActivity.typeface);
            }
            binding.errorTextViewItemFooterError.setText(R.string.load_comments_failed);
            binding.errorTextViewItemFooterError.setTextColor(mSecondaryTextColor);
            binding.retryButtonItemFooterError.setOnClickListener(view -> mRetryLoadingMoreCallback.retryLoadingMore());
            binding.retryButtonItemFooterError.setBackgroundTintList(ColorStateList.valueOf(mColorPrimaryLightTheme));
            binding.retryButtonItemFooterError.setTextColor(mButtonTextColor);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        ItemFooterLoadingBinding binding;

        LoadingViewHolder(@NonNull ItemFooterLoadingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.progressBarItemFooterLoading.setIndicatorColor(mColorAccent);
        }
    }
}
