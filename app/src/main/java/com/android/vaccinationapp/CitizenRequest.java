package com.example.vaccination;

import android.os.Parcel;
import android.os.Parcelable;

public class CitizenRequest extends Citizen implements Parcelable {

    public int id_request;
    public String request_date;
    public String request_state;

    public static final Parcelable.Creator<CitizenRequest> CREATOR = new Parcelable.Creator<CitizenRequest>() {
        @Override
        public CitizenRequest createFromParcel(Parcel source) {
            return new CitizenRequest(source);
        }

        @Override
        public CitizenRequest[] newArray(int size) {
            return new CitizenRequest[size];
        }
    };

    public CitizenRequest(Parcel in) {
        super(in);
        id_request = in.readInt();
        request_date = in.readString();
        request_state = in.readString();
    }

    public CitizenRequest(){

    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(id_request);
        out.writeString(request_date);
        out.writeString(request_state);
    }
}