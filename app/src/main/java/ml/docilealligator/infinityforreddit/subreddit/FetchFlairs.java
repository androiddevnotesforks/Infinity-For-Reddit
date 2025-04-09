package ml.docilealligator.infinityforreddit.subreddit;

import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import ml.docilealligator.infinityforreddit.apis.RedditAPI;
import ml.docilealligator.infinityforreddit.utils.APIUtils;
import ml.docilealligator.infinityforreddit.utils.JSONUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FetchFlairs {
    public static void fetchFlairsInSubreddit(Executor executor, Handler handler, Retrofit oauthRetrofit, String accessToken, String subredditName, FetchFlairsInSubredditListener fetchFlairsInSubredditListener) {
        executor.execute(() -> {
            RedditAPI api = oauthRetrofit.create(RedditAPI.class);

            Call<String> flairsCall = api.getFlairs(APIUtils.getOAuthHeader(accessToken), subredditName);
            try {
                Response<String> response = flairsCall.execute();
                if (response.isSuccessful()) {
                    List<Flair> flairs = parseFlairs(response.body());
                    if (flairs != null) {
                        handler.post(() -> fetchFlairsInSubredditListener.fetchSuccessful(flairs));
                    } else {
                        handler.post(fetchFlairsInSubredditListener::fetchFailed);
                    }
                } else if (response.code() == 403) {
                    //No flairs
                    handler.post(() -> fetchFlairsInSubredditListener.fetchSuccessful(null));
                } else {
                    handler.post(fetchFlairsInSubredditListener::fetchFailed);
                }
            } catch (IOException e) {
                e.printStackTrace();
                handler.post(fetchFlairsInSubredditListener::fetchFailed);
            }
        });
    }

    @WorkerThread
    @Nullable
    private static List<Flair> parseFlairs(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            List<Flair> flairs = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    String id = jsonArray.getJSONObject(i).getString(JSONUtils.ID_KEY);
                    String text = jsonArray.getJSONObject(i).getString(JSONUtils.TEXT_KEY);
                    boolean editable = jsonArray.getJSONObject(i).getBoolean(JSONUtils.TEXT_EDITABLE_KEY);

                    flairs.add(new Flair(id, text, editable));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return flairs;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface FetchFlairsInSubredditListener {
        void fetchSuccessful(List<Flair> flairs);

        void fetchFailed();
    }
}
