package ml.docilealligator.infinityforreddit.activities;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.Locale;

import ml.docilealligator.infinityforreddit.CustomFontReceiver;
import ml.docilealligator.infinityforreddit.R;
import ml.docilealligator.infinityforreddit.account.Account;
import ml.docilealligator.infinityforreddit.customtheme.CustomThemeWrapper;
import ml.docilealligator.infinityforreddit.customviews.slidr.widget.SliderPanel;
import ml.docilealligator.infinityforreddit.events.FinishViewMediaActivityEvent;
import ml.docilealligator.infinityforreddit.font.ContentFontFamily;
import ml.docilealligator.infinityforreddit.font.ContentFontStyle;
import ml.docilealligator.infinityforreddit.font.FontFamily;
import ml.docilealligator.infinityforreddit.font.FontStyle;
import ml.docilealligator.infinityforreddit.font.TitleFontFamily;
import ml.docilealligator.infinityforreddit.font.TitleFontStyle;
import ml.docilealligator.infinityforreddit.utils.CustomThemeSharedPreferencesUtils;
import ml.docilealligator.infinityforreddit.utils.SharedPreferencesUtils;
import ml.docilealligator.infinityforreddit.utils.Utils;

public abstract class BaseActivity extends AppCompatActivity implements CustomFontReceiver {
    public static final int IGNORE_MARGIN = -1;

    private boolean immersiveInterface;
    private boolean changeStatusBarIconColor;
    private boolean transparentStatusBarAfterToolbarCollapsed;
    private boolean hasDrawerLayout = false;
    private boolean isImmersiveInterfaceApplicable = true;
    private int systemVisibilityToolbarExpanded = 0;
    private int systemVisibilityToolbarCollapsed = 0;
    private boolean shouldTrackFullscreenMediaPeekTouchEvent;
    public CustomThemeWrapper customThemeWrapper;
    public Typeface typeface;
    public Typeface titleTypeface;
    public Typeface contentTypeface;
    @Nullable
    public SliderPanel mSliderPanel;
    @Nullable
    public ViewPager2 mViewPager2;
    @Nullable
    public String accessToken;
    @NonNull
    public String accountName = Account.ANONYMOUS_ACCOUNT;
    public Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customThemeWrapper = getCustomThemeWrapper();

        SharedPreferences mSharedPreferences = getDefaultSharedPreferences();

        String language = mSharedPreferences.getString(SharedPreferencesUtils.LANGUAGE, SharedPreferencesUtils.LANGUAGE_DEFAULT_VALUE);
        Locale systemLocale = Resources.getSystem().getConfiguration().locale;
        Locale locale;
        if (language.equals(SharedPreferencesUtils.LANGUAGE_DEFAULT_VALUE)) {
            language = systemLocale.getLanguage();
            locale = new Locale(language, systemLocale.getCountry());
        } else {
            if (language.contains("-")) {
                locale = new Locale(language.substring(0, 2), language.substring(4));
            } else {
                locale = new Locale(language);
            }
        }
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        boolean systemDefault = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
        int systemThemeType = Integer.parseInt(mSharedPreferences.getString(SharedPreferencesUtils.THEME_KEY, "2"));
        immersiveInterface = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
                mSharedPreferences.getBoolean(SharedPreferencesUtils.IMMERSIVE_INTERFACE_KEY, true)) || Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM;
        if (immersiveInterface && config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            immersiveInterface = !mSharedPreferences.getBoolean(SharedPreferencesUtils.DISABLE_IMMERSIVE_INTERFACE_IN_LANDSCAPE_MODE, false);
        }
        switch (systemThemeType) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
                getTheme().applyStyle(R.style.Theme_Normal, true);
                customThemeWrapper.setThemeType(CustomThemeSharedPreferencesUtils.LIGHT);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
                if(mSharedPreferences.getBoolean(SharedPreferencesUtils.AMOLED_DARK_KEY, false)) {
                    getTheme().applyStyle(R.style.Theme_Normal_AmoledDark, true);
                    customThemeWrapper.setThemeType(CustomThemeSharedPreferencesUtils.AMOLED);
                } else {
                    getTheme().applyStyle(R.style.Theme_Normal_NormalDark, true);
                    customThemeWrapper.setThemeType(CustomThemeSharedPreferencesUtils.DARK);
                }
                break;
            case 2:
                if (systemDefault) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM);
                } else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_AUTO_BATTERY);
                }
                if((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_NO) {
                    getTheme().applyStyle(R.style.Theme_Normal, true);
                    customThemeWrapper.setThemeType(CustomThemeSharedPreferencesUtils.LIGHT);
                } else {
                    if(mSharedPreferences.getBoolean(SharedPreferencesUtils.AMOLED_DARK_KEY, false)) {
                        getTheme().applyStyle(R.style.Theme_Normal_AmoledDark, true);
                        customThemeWrapper.setThemeType(CustomThemeSharedPreferencesUtils.AMOLED);
                    } else {
                        getTheme().applyStyle(R.style.Theme_Normal_NormalDark, true);
                        customThemeWrapper.setThemeType(CustomThemeSharedPreferencesUtils.DARK);
                    }
                }
        }

        boolean userDefinedChangeStatusBarIconColorInImmersiveInterface =
                customThemeWrapper.isChangeStatusBarIconColorAfterToolbarCollapsedInImmersiveInterface();
        if (immersiveInterface && isImmersiveInterfaceApplicable) {
            changeStatusBarIconColor = userDefinedChangeStatusBarIconColorInImmersiveInterface;
        } else {
            changeStatusBarIconColor = false;
        }

        getTheme().applyStyle(FontStyle.valueOf(mSharedPreferences
                .getString(SharedPreferencesUtils.FONT_SIZE_KEY, FontStyle.Normal.name())).getResId(), true);

        getTheme().applyStyle(TitleFontStyle.valueOf(mSharedPreferences
                .getString(SharedPreferencesUtils.TITLE_FONT_SIZE_KEY, TitleFontStyle.Normal.name())).getResId(), true);

        getTheme().applyStyle(ContentFontStyle.valueOf(mSharedPreferences
                .getString(SharedPreferencesUtils.CONTENT_FONT_SIZE_KEY, ContentFontStyle.Normal.name())).getResId(), true);

        getTheme().applyStyle(FontFamily.valueOf(mSharedPreferences
                .getString(SharedPreferencesUtils.FONT_FAMILY_KEY, FontFamily.Default.name())).getResId(), true);

        getTheme().applyStyle(TitleFontFamily.valueOf(mSharedPreferences
                .getString(SharedPreferencesUtils.TITLE_FONT_FAMILY_KEY, TitleFontFamily.Default.name())).getResId(), true);

        getTheme().applyStyle(ContentFontFamily.valueOf(mSharedPreferences
                .getString(SharedPreferencesUtils.CONTENT_FONT_FAMILY_KEY, ContentFontFamily.Default.name())).getResId(), true);

        Window window = getWindow();
        View decorView = window.getDecorView();
        boolean isLightStatusbar = customThemeWrapper.isLightStatusBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean isLightNavBar = customThemeWrapper.isLightNavBar();
            if (isLightStatusbar) {
                if (isLightNavBar) {
                    systemVisibilityToolbarExpanded = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                    if (changeStatusBarIconColor) {
                        systemVisibilityToolbarCollapsed = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                    } else {
                        systemVisibilityToolbarCollapsed = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                    }
                } else {
                    systemVisibilityToolbarExpanded = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    if (!changeStatusBarIconColor) {
                        systemVisibilityToolbarCollapsed = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    }
                }
            } else {
                if (isLightNavBar) {
                    systemVisibilityToolbarExpanded = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                    if (changeStatusBarIconColor) {
                        systemVisibilityToolbarCollapsed = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                    }
                } else {
                    if (changeStatusBarIconColor) {
                        systemVisibilityToolbarCollapsed = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    }
                }
            }
            decorView.setSystemUiVisibility(systemVisibilityToolbarExpanded);
            if (!(immersiveInterface && isImmersiveInterfaceApplicable)) {
                window.setNavigationBarColor(customThemeWrapper.getNavBarColor());
                if (!hasDrawerLayout) {
                    window.setStatusBarColor(customThemeWrapper.getColorPrimaryDark());
                }
            } else {
                window.setNavigationBarColor(Color.TRANSPARENT);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isLightStatusbar) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                systemVisibilityToolbarExpanded = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                if (!changeStatusBarIconColor) {
                    systemVisibilityToolbarCollapsed = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
            } else if (changeStatusBarIconColor) {
                systemVisibilityToolbarCollapsed = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        }

        accessToken = getCurrentAccountSharedPreferences().getString(SharedPreferencesUtils.ACCESS_TOKEN, null);
        accountName = getCurrentAccountSharedPreferences().getString(SharedPreferencesUtils.ACCOUNT_NAME, Account.ANONYMOUS_ACCOUNT);

        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && mSliderPanel != null) {
            setTranslucent(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && mSliderPanel != null && !isFinishing()) {
            mHandler.postDelayed(() -> setTranslucent(false), 500);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (shouldTrackFullscreenMediaPeekTouchEvent) {
            if (ev.getAction() == MotionEvent.ACTION_CANCEL || ev.getAction() == MotionEvent.ACTION_UP) {
                shouldTrackFullscreenMediaPeekTouchEvent = false;
                EventBus.getDefault().post(new FinishViewMediaActivityEvent());
            }
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    public abstract SharedPreferences getDefaultSharedPreferences();

    public abstract SharedPreferences getCurrentAccountSharedPreferences();

    public abstract CustomThemeWrapper getCustomThemeWrapper();

    protected abstract void applyCustomTheme();

    protected boolean isChangeStatusBarIconColor() {
        return changeStatusBarIconColor;
    }

    protected int getSystemVisibilityToolbarExpanded() {
        return systemVisibilityToolbarExpanded;
    }

    protected int getSystemVisibilityToolbarCollapsed() {
        return systemVisibilityToolbarCollapsed;
    }

    public boolean isImmersiveInterface() {
        return immersiveInterface;
    }

    protected void setToolbarGoToTop(Toolbar toolbar) {
        toolbar.setOnLongClickListener(view -> {
            if (BaseActivity.this instanceof ActivityToolbarInterface) {
                ((ActivityToolbarInterface) BaseActivity.this).onLongPress();
            }
            return true;
        });
    }

    /*protected void adjustToolbar(Toolbar toolbar) {
        int statusBarResourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarResourceId > 0) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
            params.topMargin = getResources().getDimensionPixelSize(statusBarResourceId);
            toolbar.setLayoutParams(params);
        }
    }*/

    protected void addOnOffsetChangedListener(AppBarLayout appBarLayout) {
        View decorView = getWindow().getDecorView();
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                if (state == State.COLLAPSED) {
                    decorView.setSystemUiVisibility(getSystemVisibilityToolbarCollapsed());
                } else if (state == State.EXPANDED) {
                    decorView.setSystemUiVisibility(getSystemVisibilityToolbarExpanded());
                }
            }
        });
    }

    /*public int getNavBarHeight() {
        if (isImmersiveInterfaceApplicable && immersiveInterface && getDefaultSharedPreferences().getBoolean(SharedPreferencesUtils.IMMERSIVE_INTERFACE_IGNORE_NAV_BAR_KEY, false)) {
            return 0;
        }

        Resources resources = getResources();
        int navBarResourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (navBarResourceId > 0) {
            return resources.getDimensionPixelSize(navBarResourceId);
        }
        return 0;
    }*/

    /*public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }*/

    public static <T extends View> void setMargins(T view, int left, int top, int right, int bottom) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) lp;

            if (top >= 0) {
                marginParams.topMargin = top;
            }
            if (bottom >= 0) {
                marginParams.bottomMargin = bottom;
            }
            if (left >= 0) {
                marginParams.setMarginStart(left);
            }
            if (right >= 0) {
                marginParams.setMarginEnd(right);
            }

            view.setLayoutParams(marginParams);
        }
    }

    protected void setTransparentStatusBarAfterToolbarCollapsed() {
        this.transparentStatusBarAfterToolbarCollapsed = true;
    }

    protected void setHasDrawerLayout() {
        hasDrawerLayout = true;
    }

    public void setImmersiveModeNotApplicableBelowAndroid16() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            return;
        }
        isImmersiveInterfaceApplicable = false;
    }

    protected void applyAppBarLayoutAndCollapsingToolbarLayoutAndToolbarTheme(AppBarLayout appBarLayout, @Nullable CollapsingToolbarLayout collapsingToolbarLayout, Toolbar toolbar) {
        applyAppBarLayoutAndCollapsingToolbarLayoutAndToolbarTheme(appBarLayout, collapsingToolbarLayout, toolbar, true);
    }

    protected void applyAppBarLayoutAndCollapsingToolbarLayoutAndToolbarTheme(AppBarLayout appBarLayout, @Nullable CollapsingToolbarLayout collapsingToolbarLayout, Toolbar toolbar, boolean setToolbarBackgroundColor) {
        appBarLayout.setBackgroundColor(customThemeWrapper.getColorPrimary());
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setContentScrimColor(customThemeWrapper.getColorPrimary());
        }
        if (setToolbarBackgroundColor) {
            toolbar.setBackgroundColor(customThemeWrapper.getColorPrimary());
        } else if (!isImmersiveInterface()) {
            int[] colors = {customThemeWrapper.getColorPrimary(), Color.TRANSPARENT};
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
            toolbar.setBackground(gradientDrawable);
        }
        toolbar.setTitleTextColor(customThemeWrapper.getToolbarPrimaryTextAndIconColor());
        toolbar.setSubtitleTextColor(customThemeWrapper.getToolbarSecondaryTextColor());
        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(customThemeWrapper.getToolbarPrimaryTextAndIconColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if (toolbar.getOverflowIcon() != null) {
            toolbar.getOverflowIcon().setColorFilter(customThemeWrapper.getToolbarPrimaryTextAndIconColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if (typeface != null) {
            toolbar.addOnLayoutChangeListener((view, i, i1, i2, i3, i4, i5, i6, i7) -> {
                for (int j = 0; j < toolbar.getChildCount(); j++) {
                    if (toolbar.getChildAt(j) instanceof TextView) {
                        ((TextView) toolbar.getChildAt(j)).setTypeface(typeface);
                    }
                }
            });
        }
    }

    @SuppressLint("RestrictedApi")
    protected boolean applyMenuItemTheme(Menu menu) {
        if (customThemeWrapper != null) {
            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.getItem(i);
                if (((MenuItemImpl) item).requestsActionButton()) {
                    MenuItemCompat.setIconTintList(item, ColorStateList
                            .valueOf(customThemeWrapper.getToolbarPrimaryTextAndIconColor()));
                }
                Utils.setTitleWithCustomFontToMenuItem(typeface, item, null);
            }
        }
        return true;
    }

    protected void applyTabLayoutTheme(TabLayout tabLayout) {
        int toolbarAndTabBackgroundColor = customThemeWrapper.getColorPrimary();
        tabLayout.setBackgroundColor(toolbarAndTabBackgroundColor);
        tabLayout.setSelectedTabIndicatorColor(customThemeWrapper.getTabLayoutWithCollapsedCollapsingToolbarTabIndicator());
        tabLayout.setTabTextColors(customThemeWrapper.getTabLayoutWithCollapsedCollapsingToolbarTextColor(),
                customThemeWrapper.getTabLayoutWithCollapsedCollapsingToolbarTextColor());
    }

    protected void applyFABTheme(FloatingActionButton fab) {
        fab.setBackgroundTintList(ColorStateList.valueOf(customThemeWrapper.getColorAccent()));
        fab.setImageTintList(ColorStateList.valueOf(customThemeWrapper.getFABIconColor()));
    }

    protected void fixViewPager2Sensitivity(ViewPager2 viewPager2) {
        try {
            Field recyclerViewField = ViewPager2.class.getDeclaredField("mRecyclerView");
            recyclerViewField.setAccessible(true);

            RecyclerView recyclerView = (RecyclerView) recyclerViewField.get(viewPager2);

            Field touchSlopField = RecyclerView.class.getDeclaredField("mTouchSlop");
            touchSlopField.setAccessible(true);

            Object touchSlopBox = touchSlopField.get(recyclerView);
            if (touchSlopBox != null) {
                int touchSlop = (int) touchSlopBox;
                touchSlopField.set(recyclerView, touchSlop * 4);
            }
        } catch (NoSuchFieldException | IllegalAccessException ignore) {}
    }

    protected void setOtherActivitiesFabContentDescription(FloatingActionButton fab, int fabOption) {
        switch (fabOption) {
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_SUBMIT_POSTS:
                fab.setContentDescription(getString(R.string.content_description_submit_post));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_REFRESH:
                fab.setContentDescription(getString(R.string.content_description_refresh));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_CHANGE_SORT_TYPE:
                fab.setContentDescription(getString(R.string.content_description_change_sort_type));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_CHANGE_POST_LAYOUT:
                fab.setContentDescription(getString(R.string.content_description_change_post_layout));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_SEARCH:
                fab.setContentDescription(getString(R.string.content_description_search));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_GO_TO_SUBREDDIT:
                fab.setContentDescription(getString(R.string.content_description_go_to_subreddit));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_GO_TO_USER:
                fab.setContentDescription(getString(R.string.content_description_go_to_user));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_RANDOM:
                fab.setContentDescription(getString(R.string.content_description_random));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_HIDE_READ_POSTS:
                fab.setContentDescription(getString(R.string.content_description_hide_read_posts));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_FILTER_POSTS:
                fab.setContentDescription(getString(R.string.content_description_filter_posts));
                break;
            case SharedPreferencesUtils.OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_GO_TO_TOP:
                fab.setContentDescription(getString(R.string.content_description_go_to_top));
                break;
        }
    }

    @Override
    public void setCustomFont(Typeface typeface, Typeface titleTypeface, Typeface contentTypeface) {
        this.typeface = typeface;
        this.titleTypeface = titleTypeface;
        this.contentTypeface = contentTypeface;
    }


    public void lockSwipeRightToGoBack() {

    }

    public void unlockSwipeRightToGoBack() {

    }

    public void copyLink(String link) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText("simple text", link);
            clipboard.setPrimaryClip(clip);
            if (android.os.Build.VERSION.SDK_INT < 33) {
                Toast.makeText(this, R.string.copy_success, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.copy_link_failed, Toast.LENGTH_SHORT).show();
        }
    }

    public void triggerBackPress() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    public void setShouldTrackFullscreenMediaPeekTouchEvent(boolean value) {
        shouldTrackFullscreenMediaPeekTouchEvent = value;
    }
}
