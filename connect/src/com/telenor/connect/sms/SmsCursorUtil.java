package com.telenor.connect.sms;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;

public class SmsCursorUtil {

    private static final boolean CAN_USE_API
            = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT;
    private static final Uri smsInboxUri = CAN_USE_API
            ? Telephony.Sms.Inbox.CONTENT_URI
            : Uri.parse("content://sms/inbox");
    private static final String BODY = CAN_USE_API ? Telephony.Sms.Inbox.BODY : "body";
    private static final String READ = CAN_USE_API ? Telephony.Sms.Inbox.READ : "read";
    private static final String ADDRESS = CAN_USE_API ? Telephony.Sms.Inbox.ADDRESS : "address";
    private static final String DATE = CAN_USE_API ? Telephony.Sms.Inbox.DATE : "date";

    private static final String DEFAULT_SORT_ORDER = CAN_USE_API
            ? Telephony.Sms.Inbox.DEFAULT_SORT_ORDER
            : "date DESC";

    public static Cursor getSmsCursor(Context context, long receivedAfter) {
        Uri mUri = smsInboxUri;
        String[] mProjection = new String[] { BODY };

        String mSelectionClause = DATE + " > ?";

        String[] mSelectionArgs = new String[] { String.valueOf(receivedAfter) };

        String mSortOrder = DEFAULT_SORT_ORDER;

        return context.getContentResolver().query(
                mUri,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                mSortOrder
        );
    }

}
