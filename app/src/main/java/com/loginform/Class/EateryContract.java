package com.loginform.Class;

import android.provider.BaseColumns;

public final class EateryContract {

    private EateryContract() {}

    public static class FoodEntry implements BaseColumns {
        public static final String TABLE_NAME = "QuanAn";
        public static final String COLUMN_NAME = "TenQuan";
        public static final String COLUMN_ADDRESS = "DiaChi";

        public static final String COLUMN_NUMBER = "SDT";

        public static final String COLUMN_STAR_RATING = "SaoDanhGia";

        public static final String COLUMN_DESCRIPTION = "GioiThieu";
        public static final String COLUMN_IMAGE = "HinhAnh";
    }
}
