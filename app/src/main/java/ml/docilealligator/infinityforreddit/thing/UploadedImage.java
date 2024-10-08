package ml.docilealligator.infinityforreddit.thing;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class UploadedImage implements Parcelable {
    public String imageName;
    public String imageUrlOrKey;
    private String caption = "";

    public UploadedImage(String imageName, String imageUrlOrKey) {
        this.imageName = imageName;
        this.imageUrlOrKey = imageUrlOrKey;
    }

    protected UploadedImage(Parcel in) {
        imageName = in.readString();
        imageUrlOrKey = in.readString();
        caption = in.readString();
    }

    public String getCaption() {
        return caption == null ? "" : caption;
    }

    public void setCaption(String caption) {
        this.caption = caption == null ? "" : caption;
    }

    public static final Creator<UploadedImage> CREATOR = new Creator<UploadedImage>() {
        @Override
        public UploadedImage createFromParcel(Parcel in) {
            return new UploadedImage(in);
        }

        @Override
        public UploadedImage[] newArray(int size) {
            return new UploadedImage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageName);
        parcel.writeString(imageUrlOrKey);
        parcel.writeString(caption);
    }

    public static String getArrayListJSONModel(ArrayList<UploadedImage> uploadedImages) {
        return new Gson().toJson(uploadedImages, new TypeToken<List<UploadedImage>>(){}.getType());
    }

    public static List<UploadedImage> fromListJson(String json) throws JsonParseException {
        return new Gson().fromJson(json, new TypeToken<List<UploadedImage>>(){}.getType());
    }
}
