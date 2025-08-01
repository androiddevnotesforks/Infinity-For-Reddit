package ml.docilealligator.infinityforreddit.utils;

import android.view.Display;

import androidx.annotation.Nullable;

/**
 * Created by alex on 2/23/18.
 */

public class SharedPreferencesUtils {
    public static final String ENABLE_NOTIFICATION_KEY = "enable_notification";
    public static final String NOTIFICATION_INTERVAL_KEY = "notificaiton_interval";
    public static final String LAZY_MODE_INTERVAL_KEY = "lazy_mode_interval";
    public static final String THEME_KEY = "theme";
    public static final String ICON_FOREGROUND_KEY = "icon_foreground";
    public static final String ICON_BACKGROUND_KEY = "icon_background";
    public static final String ERROR_IMAGE_KEY = "error_image";
    public static final String CROSSPOST_ICON_KEY = "crosspost_icon";
    public static final String THUMBTACK_ICON_KEY = "thumbtack_icon";
    public static final String BEST_ROCKET_ICON_KEY = "best_rocket_icon";
    public static final String MATERIAL_ICONS_KEY = "material_icons";
    public static final String OPEN_SOURCE_KEY = "open_source";
    public static final String RATE_KEY = "rate";
    public static final String EMAIL_KEY = "email";
    public static final String REDDIT_ACCOUNT_KEY = "reddit_account";
    public static final String SUBREDDIT_KEY = "subreddit";
    public static final String SHARE_KEY = "share";
    public static final String PRIVACY_POLICY_KEY = "privacy_policy";
    public static final String VERSION_KEY = "version";
    public static final String FONT_SIZE_KEY = "font_size";
    public static final String TITLE_FONT_SIZE_KEY = "title_font_size";
    public static final String CONTENT_FONT_SIZE_KEY = "content_font_size";
    public static final String FONT_FAMILY_KEY = "font_family";
    public static final String TITLE_FONT_FAMILY_KEY = "title_font_family";
    public static final String CONTENT_FONT_FAMILY_KEY = "content_font_family";
    public static final String AMOLED_DARK_KEY = "amoled_dark";
    public static final String IMMERSIVE_INTERFACE_ENTRY_KEY = "immersive_interface_entry";
    public static final String IMMERSIVE_INTERFACE_KEY = "immersive_interface";
    public static final String IMMERSIVE_INTERFACE_IGNORE_NAV_BAR_KEY = "immersive_interface_ignore_nav_bar";
    public static final String DISABLE_IMMERSIVE_INTERFACE_IN_LANDSCAPE_MODE = "disable_immersive_interface_in_landscape_mode";
    public static final String BOTTOM_APP_BAR_KEY = "bottom_app_bar";
    public static final String VOTE_BUTTONS_ON_THE_RIGHT_KEY = "vote_buttons_on_the_right";
    public static final String SHOW_AVATAR_ON_THE_RIGHT = "show_avatar_on_the_right";
    public static final String DEFAULT_SEARCH_RESULT_TAB = "default_search_result_tab";
    public static final String CUSTOM_FONT_FAMILY_KEY = "custom_font_family";
    public static final String CUSTOM_TITLE_FONT_FAMILY_KEY = "custom_title_font_family";
    public static final String CUSTOM_CONTENT_FONT_FAMILY_KEY = "custom_content_font_family";
    public static final String REDDIT_USER_AGREEMENT_KEY = "reddit_user_agreement";
    public static final String HIDE_FAB_IN_POST_FEED = "hide_fab_in_post_feed";

    public static final String SORT_TYPE_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.sort_type";
    public static final String SORT_TYPE_BEST_POST = "sort_type_best_post";
    public static final String SORT_TIME_BEST_POST = "sort_time_best_post";
    public static final String SORT_TYPE_SEARCH_POST = "sort_type_search_post";
    public static final String SORT_TIME_SEARCH_POST = "sort_time_search_post";
    public static final String SORT_TYPE_SUBREDDIT_POST_BASE = "sort_type_subreddit_post_";
    public static final String SORT_TIME_SUBREDDIT_POST_BASE = "sort_time_subreddit_post_";
    public static final String SORT_TYPE_MULTI_REDDIT_POST_BASE = "sort_type_multi_reddit_post_";
    public static final String SORT_TIME_MULTI_REDDIT_POST_BASE = "sort_time_multi_reddit_post_";
    public static final String SORT_TYPE_USER_POST_BASE = "sort_type_user_post_";
    public static final String SORT_TIME_USER_POST_BASE = "sort_time_user_post_";
    public static final String SORT_TYPE_USER_COMMENT = "sort_type_user_comment";
    public static final String SORT_TIME_USER_COMMENT = "sort_time_user_comment";
    public static final String SORT_TYPE_SEARCH_SUBREDDIT = "sort_type_search_subreddit";
    public static final String SORT_TYPE_SEARCH_USER = "sort_type_search_user";
    public static final String SORT_TYPE_POST_COMMENT = "sort_type_post_comment";

    public static final String POST_LAYOUT_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.post_layout";
    public static final String POST_LAYOUT_FRONT_PAGE_POST = "post_layout_best_post";
    public static final String POST_LAYOUT_SUBREDDIT_POST_BASE = "post_layout_subreddit_post_";
    public static final String POST_LAYOUT_MULTI_REDDIT_POST_BASE = "post_layout_multi_reddit_post_";
    public static final String POST_LAYOUT_USER_POST_BASE = "post_layout_user_post_";
    public static final String POST_LAYOUT_SEARCH_POST = "post_layout_search_post";
    public static final String HISTORY_POST_LAYOUT_READ_POST = "history_post_layout_read_post";
    public static final int POST_LAYOUT_CARD = 0;
    public static final int POST_LAYOUT_COMPACT = 1;
    public static final int POST_LAYOUT_GALLERY = 2;
    public static final int POST_LAYOUT_CARD_2 = 3;
    public static final int POST_LAYOUT_CARD_3 = 4;
    public static final int POST_LAYOUT_COMPACT_2 = 5;

    public static final String FRONT_PAGE_SCROLLED_POSITION_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.front_page_scrolled_position";
    public static final String FRONT_PAGE_SCROLLED_POSITION_FRONT_PAGE_BASE = "_front_page";
    public static final String FRONT_PAGE_SCROLLED_POSITION_ANONYMOUS = ".anonymous";

    public static final String PULL_NOTIFICATION_TIME = "pull_notification_time";
    public static final String SHOW_ELAPSED_TIME_KEY = "show_elapsed_time";
    public static final String TIME_FORMAT_KEY = "time_format";
    public static final String TIME_FORMAT_DEFAULT_VALUE = "MMM d, yyyy, HH:mm";
    public static final String DEFAULT_POST_LAYOUT_KEY = "default_post_layout";
    public static final String SHOW_DIVIDER_IN_COMPACT_LAYOUT = "show_divider_in_compact_layout";
    public static final String SHOW_THUMBNAIL_ON_THE_LEFT_IN_COMPACT_LAYOUT = "show_thumbnail_on_the_left_in_compact_layout";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_PORTRAIT = "number_of_columns_in_post_feed_portrait";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_LANDSCAPE = "number_of_columns_in_post_feed_landscape";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_PORTRAIT_UNFOLDED = "number_of_columns_in_post_feed_portrait_unfolded";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_LANDSCAPE_UNFOLDED = "number_of_columns_in_post_feed_landscape_unfolded";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_PORTRAIT_COMPACT_LAYOUT = "number_of_columns_in_post_feed_portrait_compact_layout";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_LANDSCAPE_COMPACT_LAYOUT = "number_of_columns_in_post_feed_landscape_compact_layout";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_PORTRAIT_GALLERY_LAYOUT = "number_of_columns_in_post_feed_portrait_gallery_layout";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_LANDSCAPE_GALLERY_LAYOUT = "number_of_columns_in_post_feed_landscape_gallery_layout";
    public static final String SWIPE_RIGHT_TO_GO_BACK = "swipe_to_go_back_from_post_detail";
    public static final String SWIPE_VERTICALLY_TO_GO_BACK_FROM_MEDIA = "swipe_vertically_to_go_back_from_media";
    public static final String VOLUME_KEYS_NAVIGATE_COMMENTS = "volume_keys_navigate_comments";
    public static final String VOLUME_KEYS_NAVIGATE_POSTS = "volume_keys_navigate_posts";
    public static final String MUTE_VIDEO = "mute_video";
    public static final String LINK_HANDLER = "link_handler";
    public static final String VIDEO_AUTOPLAY = "video_autoplay";
    public static final String VIDEO_AUTOPLAY_VALUE_ALWAYS_ON = "2";
    public static final String VIDEO_AUTOPLAY_VALUE_ON_WIFI = "1";
    public static final String VIDEO_AUTOPLAY_VALUE_NEVER = "0";
    public static final String MUTE_AUTOPLAYING_VIDEOS = "mute_autoplaying_videos";
    public static final String AUTOPLAY_NSFW_VIDEOS = "autoplay_nsfw_videos";
    public static final String LOCK_JUMP_TO_NEXT_TOP_LEVEL_COMMENT_BUTTON = "lock_jump_to_next_top_level_comment_button";
    public static final String SWAP_TAP_AND_LONG_COMMENTS = "swap_tap_and_long_in_comments";
    public static final String SWIPE_UP_TO_HIDE_JUMP_TO_NEXT_TOP_LEVEL_COMMENT_BUTTON = "swipe_up_to_hide_jump_to_next_top_level_comments_button";
    public static final String SHOW_TOP_LEVEL_COMMENTS_FIRST = "show_top_level_comments_first";
    public static final String MAIN_PAGE_BACK_BUTTON_ACTION = "main_page_back_button_action";
    public static final int MAIN_PAGE_BACK_BUTTON_ACTION_CONFIRM_EXIT = 1;
    public static final int MAIN_PAGE_BACK_BUTTON_ACTION_OPEN_NAVIGATION_DRAWER = 2;
    public static final String LOCK_BOTTOM_APP_BAR = "lock_bottom_app_bar";
    public static final String COMMENT_TOOLBAR_HIDDEN = "comment_toolbar_hidden";
    public static final String COMMENT_TOOLBAR_HIDE_ON_CLICK = "comment_toolbar_hide_on_click";
    public static final String FULLY_COLLAPSE_COMMENT = "fully_collapse_comment";
    public static final String SHOW_COMMENT_DIVIDER = "show_comment_divider";
    public static final String SHOW_ABSOLUTE_NUMBER_OF_VOTES = "show_absolute_number_of_votes";
    public static final String CUSTOMIZE_LIGHT_THEME = "customize_light_theme";
    public static final String CUSTOMIZE_DARK_THEME = "customize_dark_theme";
    public static final String CUSTOMIZE_AMOLED_THEME = "customize_amoled_theme";
    public static final String MANAGE_THEMES = "manage_themes";
    public static final String DELETE_ALL_SUBREDDITS_DATA_IN_DATABASE = "delete_all_subreddits_data_in_database";
    public static final String DELETE_ALL_USERS_DATA_IN_DATABASE = "delete_all_users_data_in_database";
    public static final String DELETE_ALL_SORT_TYPE_DATA_IN_DATABASE = "delete_all_sort_type_data_in_database";
    public static final String DELETE_ALL_POST_LAYOUT_DATA_IN_DATABASE = "delete_all_post_layout_data_in_database";
    public static final String DELETE_ALL_THEMES_IN_DATABASE = "delete_all_themes_in_database";
    public static final String DELETE_FRONT_PAGE_SCROLLED_POSITIONS_IN_DATABASE = "delete_front_page_scrolled_positions_in_database";
    public static final String DELETE_READ_POSTS_IN_DATABASE = "delete_read_posts_in_database";
    public static final String DELETE_ALL_LEGACY_SETTINGS = "delete_all_legacy_settings";
    public static final String RESET_ALL_SETTINGS = "reset_all_settings";
    public static final String IMAGE_DOWNLOAD_LOCATION = "image_download_location";
    public static final String GIF_DOWNLOAD_LOCATION = "gif_download_location";
    public static final String VIDEO_DOWNLOAD_LOCATION = "video_download_location";
    public static final String SEPARATE_FOLDER_FOR_EACH_SUBREDDIT = "separate_folder_for_each_subreddit";
    public static final String SAVE_NSFW_MEDIA_IN_DIFFERENT_FOLDER = "save_nsfw_media_in_different_folder";
    public static final String NSFW_DOWNLOAD_LOCATION = "nsfw_download_location";
    public static final String VIBRATE_WHEN_ACTION_TRIGGERED = "vibrate_when_action_triggered";
    public static final String DISABLE_SWIPING_BETWEEN_TABS = "disable_swiping_between_tabs";
    public static final String ENABLE_SWIPE_ACTION = "enable_swipe_action";
    public static final String SWIPE_ACTION_THRESHOLD = "swipe_action_threshold";
    public static final String PULL_TO_REFRESH = "pull_to_refresh";
    public static final String LONG_PRESS_TO_HIDE_TOOLBAR_IN_COMPACT_LAYOUT = "long_press_to_hide_toolbar_in_compact_layout";
    public static final String POST_COMPACT_LAYOUT_TOOLBAR_HIDDEN_BY_DEFAULT = "post_compact_layout_toolbar_hidden_by_default";
    public static final String SECURITY = "security";
    public static final String START_AUTOPLAY_VISIBLE_AREA_OFFSET_PORTRAIT = "start_autoplay_visible_area_offset_portrait";
    public static final String START_AUTOPLAY_VISIBLE_AREA_OFFSET_LANDSCAPE = "start_autoplay_visible_area_offset_landscape";
    public static final String MUTE_NSFW_VIDEO = "mute_nsfw_video";
    public static final String VIDEO_PLAYER_IGNORE_NAV_BAR = "video_player_ignore_nav_bar";
    public static final String SAVE_FRONT_PAGE_SCROLLED_POSITION = "save_front_page_scrolled_position";
    public static final String DATA_SAVING_MODE = "data_saving_mode";
    public static final String DATA_SAVING_MODE_OFF = "0";
    public static final String DATA_SAVING_MODE_ONLY_ON_CELLULAR_DATA = "1";
    public static final String DATA_SAVING_MODE_ALWAYS = "2";
    public static final String NATIONAL_FLAGS = "national_flags";
    public static final String RESPECT_SUBREDDIT_RECOMMENDED_COMMENT_SORT_TYPE = "respect_subreddit_recommended_comment_sort_type";
    public static final String UFO_CAPTURING_ANIMATION = "ufo_capturing_animation";
    public static final String HIDE_SUBREDDIT_DESCRIPTION = "hide_subreddit_description";
    public static final String DISABLE_IMAGE_PREVIEW = "disable_image_preview";
    public static final String SWIPE_LEFT_ACTION = "swipe_left_action";
    public static final String SWIPE_RIGHT_ACTION = "swipe_right_action";
    public static final int SWIPE_ACITON_UPVOTE = 0;
    public static final int SWIPE_ACITON_DOWNVOTE = 1;
    public static final String LANGUAGE = "language";
    public static final String LANGUAGE_DEFAULT_VALUE = "auto";
    public static final String ENABLE_SEARCH_HISTORY = "enable_search_history";
    public static final String POST_FILTER = "post_filter";
    public static final String ONLY_DISABLE_PREVIEW_IN_VIDEO_AND_GIF_POSTS = "only_disable_preview_in_video_and_gif_posts";
    public static final String SAVE_SORT_TYPE = "save_sort_type";
    public static final String SUBREDDIT_DEFAULT_SORT_TYPE = "subreddit_default_sort_type";
    public static final String SUBREDDIT_DEFAULT_SORT_TIME = "subreddit_default_sort_time";
    public static final String USER_DEFAULT_SORT_TYPE = "user_default_sort_type";
    public static final String USER_DEFAULT_SORT_TIME = "user_default_sort_time";
    public static final String CLICK_TO_SHOW_MEDIA_IN_GALLERY_LAYOUT = "click_to_show_media_in_gallery_layout";
    public static final String HIDE_POST_TYPE = "hide_post_type";
    public static final String HIDE_POST_FLAIR = "hide_post_flair";
    public static final String HIDE_SUBREDDIT_AND_USER_PREFIX = "hide_subreddit_and_user_prefix";
    public static final String HIDE_THE_NUMBER_OF_VOTES = "hide_the_number_of_votes";
    public static final String HIDE_THE_NUMBER_OF_COMMENTS = "hide_the_number_of_comments";
    public static final String BACKUP_SETTINGS = "backup_settings";
    public static final String RESTORE_SETTINGS = "restore_settings";
    public static final String SHOW_SUICIDE_PREVENTION_ACTIVITY = "show_suicide_prevention_activity";
    public static final String LOVE_ANIMATION = "love_animation";
    public static final String SWIPE_BETWEEN_POSTS = "swipe_between_posts";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_PORTRAIT_CARD_LAYOUT_2 = "number_of_columns_in_post_feed_portrait_card_layout_2";
    public static final String NUMBER_OF_COLUMNS_IN_POST_FEED_LANDSCAPE_CARD_LAYOUT_2 = "number_of_columns_in_post_feed_landscape_card_layout_2";
    public static final String DISABLE_NSFW_FOREVER = "disable_nsfw_forever";
    public static final String SHOW_ONLY_ONE_COMMENT_LEVEL_INDICATOR = "show_only_one_comment_level_indicator";
    public static final String ENABLE_MATERIAL_YOU = "enable_material_you";
    public static final String APPLY_MATERIAL_YOU = "apply_material_you";
    public static final String VIDEO_PLAYER_AUTOMATIC_LANDSCAPE_ORIENTATION = "video_player_automatic_landscape_orientation";
    public static final String REMEMBER_MUTING_OPTION_IN_POST_FEED = "remember_muting_option_in_post_feed";
    public static final String DEFAULT_LINK_POST_LAYOUT_KEY = "default_link_post_layout";
    public static final String USE_BOTTOM_TOOLBAR_IN_MEDIA_VIEWER = "use_bottom_toolbar_in_media_viewer";
    public static final String HIDE_ACCOUNT_KARMA_NAV_BAR = "hide_account_karma";
    public static final String LOCK_SCREEN_ANIMATION = "lock_screen_animation";
    public static final String ENABLE_FOLD_SUPPORT = "enable_fold_support";
    public static final String LOOP_VIDEO = "loop_video";
    public static final String DEFAULT_PLAYBACK_SPEED = "default_playback_speed";
    public static final String LEGACY_AUTOPLAY_VIDEO_CONTROLLER_UI = "legacy_autoplay_video_controller_ui";
    public static final String PINCH_TO_ZOOM_VIDEO = "pinch_to_zoom_video";
    public static final String FIXED_HEIGHT_PREVIEW_IN_CARD = "fixed_height_preview_in_card";
    public static final String HIDE_TEXT_POST_CONTENT = "hide_text_post_content";
    public static final String SHOW_FEWER_TOOLBAR_OPTIONS_THRESHOLD = "show_fewer_toolbar_options_threshold";
    public static final String SHOW_AUTHOR_AVATAR = "show_author_avatar";
    public static final String ALWAYS_SHOW_CHILD_COMMENT_COUNT = "always_show_child_comment_count";
    public static final String HIDE_UPVOTE_RATIO = "hide_upvote_ratio";
    public static final String POST_FEED_MAX_RESOLUTION = "post_feed_max_resolution";
    public static final String REDDIT_VIDEO_DEFAULT_RESOLUTION = "reddit_video_default_resolution";
    public static final String EASIER_TO_WATCH_IN_FULL_SCREEN = "easier_to_watch_in_full_screen";
    public static final String HIDE_THE_NUMBER_OF_VOTES_IN_COMMENTS = "hide_the_number_of_votes_in_comments";
    public static final String COMMENT_DIVIDER_TYPE = "comment_divider_type";
    public static final String SUBSCRIBED_THINGS_SYNC_TIME = "subscribed_things_sync_time";
    public static final String COMMENT_FILTER = "comment_filter";
    private static final String POST_DETAIL_FAB_PORTRAIT_X_BASE = "fab_portrait_x_";
    private static final String POST_DETAIL_FAB_PORTRAIT_Y_BASE = "fab_portrait_y_";
    private static final String POST_DETAIL_FAB_LANDSCAPE_X_BASE = "fab_landscape_x_";
    private static final String POST_DETAIL_FAB_LANDSCAPE_Y_BASE = "fab_landscape_y_";
    public static final String REDDIT_VIDEO_DEFAULT_RESOLUTION_NO_DATA_SAVING = "reddit_video_default_resolution_no_data_saving";
    public static final String HIDE_FAB_IN_POST_DETAILS = "hide_fab_in_post_details";
    public static final String LONG_PRESS_POST_NON_MEDIA_AREA = "long_press_post_non_media_area";
    public static final String LONG_PRESS_POST_MEDIA = "long_press_post_media";
    public static final String LONG_PRESS_POST_VALUE_NONE = "0";
    public static final String LONG_PRESS_POST_VALUE_SHOW_POST_OPTIONS = "1";
    public static final String LONG_PRESS_POST_VALUE_PREVIEW_IN_FULLSCREEN = "2";

    public static String getPostDetailFabPortraitX(@Nullable Display display) {
        if (display == null) {
            return POST_DETAIL_FAB_PORTRAIT_X_BASE;
        }

        return POST_DETAIL_FAB_PORTRAIT_X_BASE + display.getDisplayId();
    }

    public static String getPostDetailFabPortraitY(@Nullable Display display) {
        if (display == null) {
            return POST_DETAIL_FAB_PORTRAIT_Y_BASE;
        }

        return POST_DETAIL_FAB_PORTRAIT_Y_BASE + display.getDisplayId();
    }

    public static String getPostDetailFabLandscapeX(@Nullable Display display) {
        if (display == null) {
            return POST_DETAIL_FAB_LANDSCAPE_X_BASE;
        }

        return POST_DETAIL_FAB_LANDSCAPE_X_BASE + display.getDisplayId();
    }

    public static String getPostDetailFabLandscapeY(@Nullable Display display) {
        if (display == null) {
            return POST_DETAIL_FAB_LANDSCAPE_Y_BASE;
        }

        return POST_DETAIL_FAB_LANDSCAPE_Y_BASE + display.getDisplayId();
    }

    public static final String EMBEDDED_MEDIA_TYPE = "embedded_media_type";
    public static final int EMBEDDED_MEDIA_ALL = 15;

    public static boolean canShowImage(int embeddedMediaType) {
        return embeddedMediaType == 15 || embeddedMediaType == 7 || embeddedMediaType == 6 || embeddedMediaType == 3;
    }

    public static boolean canShowGif(int embeddedMediaType) {
        return embeddedMediaType == 15 || embeddedMediaType == 7 || embeddedMediaType == 5 || embeddedMediaType == 2;
    }

    public static boolean canShowEmote(int embeddedMediaType) {
        return embeddedMediaType == 15 || embeddedMediaType == 6 || embeddedMediaType == 5 || embeddedMediaType == 1;
    }

    public static final String DEFAULT_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit_preferences";
    public static final String MAIN_PAGE_TABS_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.main_page_tabs";
    public static final String MAIN_PAGE_TAB_COUNT = "_main_page_tab_count";
    public static final String MAIN_PAGE_SHOW_TAB_NAMES = "_main_page_show_tab_names";
    public static final String MAIN_PAGE_TAB_1_TITLE = "_main_page_tab_1_title";
    public static final String MAIN_PAGE_TAB_2_TITLE = "_main_page_tab_2_title";
    public static final String MAIN_PAGE_TAB_3_TITLE = "_main_page_tab_3_title";
    public static final String MAIN_PAGE_TAB_1_POST_TYPE = "_main_page_tab_1_post_type";
    public static final String MAIN_PAGE_TAB_2_POST_TYPE = "_main_page_tab_2_post_type";
    public static final String MAIN_PAGE_TAB_3_POST_TYPE = "_main_page_tab_3_post_type";
    public static final String MAIN_PAGE_TAB_1_NAME = "_main_page_tab_1_name";
    public static final String MAIN_PAGE_TAB_2_NAME = "_main_page_tab_2_name";
    public static final String MAIN_PAGE_TAB_3_NAME = "_main_page_tab_3_name";
    public static final int MAIN_PAGE_TAB_POST_TYPE_HOME = 0;
    public static final int MAIN_PAGE_TAB_POST_TYPE_POPULAR = 1;
    public static final int MAIN_PAGE_TAB_POST_TYPE_ALL = 2;
    public static final int MAIN_PAGE_TAB_POST_TYPE_SUBREDDIT = 3;
    public static final int MAIN_PAGE_TAB_POST_TYPE_MULTIREDDIT = 4;
    public static final int MAIN_PAGE_TAB_POST_TYPE_USER = 5;
    public static final int MAIN_PAGE_TAB_POST_TYPE_UPVOTED = 6;
    public static final int MAIN_PAGE_TAB_POST_TYPE_DOWNVOTED = 7;
    public static final int MAIN_PAGE_TAB_POST_TYPE_HIDDEN = 8;
    public static final int MAIN_PAGE_TAB_POST_TYPE_SAVED = 9;
    public static final int MAIN_PAGE_TAB_POST_TYPE_GILDED = 10;
    public static final String MAIN_PAGE_SHOW_MULTIREDDITS = "_main_page_show_multireddits";
    public static final String MAIN_PAGE_SHOW_FAVORITE_MULTIREDDITS = "_main_page_show_favorite_multireddits";
    public static final String MAIN_PAGE_SHOW_SUBSCRIBED_SUBREDDITS = "_main_page_show_subscribed_subreddits";
    public static final String MAIN_PAGE_SHOW_FAVORITE_SUBSCRIBED_SUBREDDITS = "_main_page_show_favorite_subscribed_subreddits";

    public static final String BOTTOM_APP_BAR_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.bottom_app_bar";
    public static final String MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_COUNT = "main_activity_bottom_app_bar_option_count";
    public static final String MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_1 = "main_activity_bottom_app_bar_option_1";
    public static final String MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_2 = "main_activity_bottom_app_bar_option_2";
    public static final String MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_3 = "main_activity_bottom_app_bar_option_3";
    public static final String MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_4 = "main_activity_bottom_app_bar_option_4";
    public static final String MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB = "main_activity_bottom_app_bar_fab";
    public static final String OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_COUNT = "other_activities_bottom_app_bar_option_count";
    public static final String OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_1 = "other_activities_bottom_app_bar_option_1";
    public static final String OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_2 = "other_activities_bottom_app_bar_option_2";
    public static final String OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_3 = "other_activities_bottom_app_bar_option_3";
    public static final String OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_4 = "other_activities_bottom_app_bar_option_4";
    public static final String OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB = "other_activities_bottom_app_bar_fab";

    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_SUBSCRIPTIONS = 0;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_MULTIREDDITS = 1;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_INBOX = 2;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_PROFILE = 3;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_SUBMIT_POSTS = 4;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_REFRESH = 5;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_CHANGE_SORT_TYPE = 6;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_CHANGE_POST_LAYOUT = 7;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_SEARCH = 8;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_GO_TO_SUBREDDIT = 9;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_GO_TO_USER = 10;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_RANDOM = 11;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_HIDE_READ_POSTS = 12;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_FILTER_POSTS = 13;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_UPVOTED = 14;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_DOWNVOTED = 15;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_HIDDEN = 16;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_SAVED = 17;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_OPTION_GO_TO_TOP = 18;

    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_SUBMIT_POSTS = 0;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_REFRESH = 1;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_CHANGE_SORT_TYPE = 2;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_CHANGE_POST_LAYOUT = 3;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_SEARCH = 4;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_GO_TO_SUBREDDIT = 5;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_GO_TO_USER = 6;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_RANDOM = 7;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_HIDE_READ_POSTS = 8;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_FILTER_POSTS = 9;
    public static final int MAIN_ACTIVITY_BOTTOM_APP_BAR_FAB_GO_TO_TOP = 10;

    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_HOME = 0;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_SUBSCRIPTIONS = 1;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_INBOX = 2;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_PROFILE = 3;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_MULTIREDDITS = 4;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_SUBMIT_POSTS = 5;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_REFRESH = 6;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_CHANGE_SORT_TYPE = 7;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_CHANGE_POST_LAYOUT = 8;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_SEARCH = 9;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_GO_TO_SUBREDDIT = 10;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_GO_TO_USER = 11;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_RANDOM = 12;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_HIDE_READ_POSTS = 13;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_FILTER_POSTS = 14;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_UPVOTED = 15;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_DOWNVOTED = 16;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_HIDDEN = 17;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_SAVED = 18;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_OPTION_GO_TO_TOP = 19;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_SUBMIT_POSTS = 0;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_REFRESH = 1;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_CHANGE_SORT_TYPE = 2;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_CHANGE_POST_LAYOUT = 3;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_SEARCH = 4;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_GO_TO_SUBREDDIT = 5;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_GO_TO_USER = 6;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_RANDOM = 7;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_HIDE_READ_POSTS = 8;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_FILTER_POSTS = 9;
    public static final int OTHER_ACTIVITIES_BOTTOM_APP_BAR_FAB_GO_TO_TOP = 10;

    public static final String NSFW_AND_SPOILER_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.nsfw_and_spoiler";
    public static final String NSFW_BASE = "_nsfw";
    public static final String BLUR_NSFW_BASE = "_blur_nsfw";
    public static final String DO_NOT_BLUR_NSFW_IN_NSFW_SUBREDDITS = "do_not_blur_nsfw_in_nsfw_subreddits";
    public static final String BLUR_SPOILER_BASE = "_blur_spoiler";

    public static final String POST_HISTORY_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.post_history";
    public static final String MARK_POSTS_AS_READ_BASE = "_mark_posts_as_read";
    public static final String READ_POSTS_LIMIT_ENABLED = "_read_posts_limit_enabled";
    public static final String READ_POSTS_LIMIT = "_read_posts_limit";
    public static final String MARK_POSTS_AS_READ_AFTER_VOTING_BASE = "_mark_posts_as_read_after_voting";
    public static final String MARK_POSTS_AS_READ_ON_SCROLL_BASE = "_mark_posts_as_read_on_scroll";
    public static final String HIDE_READ_POSTS_AUTOMATICALLY_BASE = "_hide_read_posts_automatically";

    public static final String CURRENT_ACCOUNT_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.current_account";
    public static final String ACCOUNT_NAME = "account_name";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String ACCOUNT_IMAGE_URL = "account_image_url";
    public static final String REDGIFS_ACCESS_TOKEN = "redgifs_access_token";
    public static final String INBOX_COUNT = "inbox_count";

    public static final String NAVIGATION_DRAWER_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.navigation_drawer";
    public static final String COLLAPSE_ACCOUNT_SECTION = "collapse_account_section";
    public static final String COLLAPSE_REDDIT_SECTION = "collapse_reddit_section";
    public static final String COLLAPSE_POST_SECTION = "collapse_post_section";
    public static final String COLLAPSE_PREFERENCES_SECTION = "collapse_preferences_section";
    public static final String COLLAPSE_FAVORITE_SUBREDDITS_SECTION = "collapse_favorite_subreddits_section";
    public static final String COLLAPSE_SUBSCRIBED_SUBREDDITS_SECTION = "collapse_subscribed_subreddits_section";
    public static final String HIDE_FAVORITE_SUBREDDITS_SECTION = "hide_favorite_subreddits_sections";
    public static final String HIDE_SUBSCRIBED_SUBREDDITS_SECTIONS = "hide_subscribed_subreddits_sections";

    public static final String POST_DETAILS_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.post_details";
    public static final String SEPARATE_POST_AND_COMMENTS_IN_PORTRAIT_MODE = "separate_post_and_comments_in_portrait_mode";
    public static final String SEPARATE_POST_AND_COMMENTS_IN_LANDSCAPE_MODE = "separate_post_and_comments_in_landscape_mode";

    public static final String SECURITY_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.security";
    public static final String REQUIRE_AUTHENTICATION_TO_GO_TO_ACCOUNT_SECTION_IN_NAVIGATION_DRAWER = "require_auth_to_account_section";
    public static final String SECURE_MODE = "secure_mode";
    public static final String APP_LOCK = "app_lock";
    public static final String APP_LOCK_TIMEOUT = "app_lock_timeout";
    public static final String LAST_FOREGROUND_TIME = "last_foreground_time";

    public static final String INTERNAL_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.internal";
    public static final String HAS_REQUESTED_NOTIFICATION_PERMISSION = "has_requested_notification_permission";
    public static final String DO_NOT_SHOW_REDDIT_API_INFO_V2_AGAIN = "do_not_show_reddit_api_info_v2_again";
    public static final String MATERIAL_YOU_SENTRY_COLOR = "material_you_sentry_color";

    public static final String PROXY_SHARED_PREFERENCES_FILE = "ml.docilealligator.infinityforreddit.proxy";
    public static final String PROXY_ENABLED = "proxy_enabled";
    public static final String PROXY_TYPE = "proxy_type";
    public static final String PROXY_HOSTNAME = "proxy_hostname";
    public static final String PROXY_PORT = "proxy_port";

    //Legacy Settings
    public static final String MAIN_PAGE_TAB_1_TITLE_LEGACY = "main_page_tab_1_title";
    public static final String MAIN_PAGE_TAB_2_TITLE_LEGACY = "main_page_tab_2_title";
    public static final String MAIN_PAGE_TAB_3_TITLE_LEGACY = "main_page_tab_3_title";
    public static final String MAIN_PAGE_TAB_1_POST_TYPE_LEGACY = "main_page_tab_1_post_type";
    public static final String MAIN_PAGE_TAB_2_POST_TYPE_LEGACY = "main_page_tab_2_post_type";
    public static final String MAIN_PAGE_TAB_3_POST_TYPE_LEGACY = "main_page_tab_3_post_type";
    public static final String MAIN_PAGE_TAB_1_NAME_LEGACY = "main_page_tab_1_name";
    public static final String MAIN_PAGE_TAB_2_NAME_LEGACY = "main_page_tab_2_name";
    public static final String MAIN_PAGE_TAB_3_NAME_LEGACY = "main_page_tab_3_name";

    public static final String SORT_TYPE_ALL_POST_LEGACY = "sort_type_all_post";
    public static final String SORT_TIME_ALL_POST_LEGACY = "sort_time_all_post";
    public static final String SORT_TYPE_POPULAR_POST_LEGACY = "sort_type_popular_post";
    public static final String SORT_TIME_POPULAR_POST_LEGACY = "sort_time_popular_post";

    public static final String POST_LAYOUT_POPULAR_POST_LEGACY = "post_layout_popular_post";
    public static final String POST_LAYOUT_ALL_POST_LEGACY = "post_layout_all_post";

    public static final String NSFW_KEY_LEGACY = "nsfw";
    public static final String BLUR_NSFW_KEY_LEGACY = "blur_nsfw";
    public static final String BLUR_SPOILER_KEY_LEGACY = "blur_spoiler";
    public static final String CONFIRM_TO_EXIT_LEGACY = "confirm_to_exit";
    public static final String OPEN_LINK_IN_APP_LEGACY = "open_link_in_app";
    public static final String AUTOMATICALLY_TRY_REDGIFS_LEGACY = "automatically_try_redgifs";

    public static final String DO_NOT_SHOW_REDDIT_API_INFO_AGAIN_LEGACY = "do_not_show_reddit_api_info_again";
    public static final String HIDE_THE_NUMBER_OF_AWARDS_LEGACY = "hide_the_number_of_awards";
    public static final String HIDE_COMMENT_AWARDS_LEGACY = "hide_comment_awards";

    //Current account
    public static final String APPLICATION_ONLY_ACCESS_TOKEN_LEGACY = "app_only_access_token";
}
