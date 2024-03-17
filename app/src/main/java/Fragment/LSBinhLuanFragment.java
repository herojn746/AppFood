package Fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.loginform.Activity.LoginActivity;

import com.loginform.Adapter.AdapterDanhGia;
import com.loginform.Adapter.AdapterLSBL;
import com.loginform.Adapter.AdapterMonAn;
import com.loginform.Class.BinhLuan;
import com.loginform.Database.Database;
import com.loginform.R;

import java.util.ArrayList;

public class LSBinhLuanFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static LSBinhLuanFragment newInstance(String param1, String param2) {
        LSBinhLuanFragment fragment = new LSBinhLuanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lsbinhluan, container, false);
    }
    int ma;
    String hoten, sdt, tentk, matkhau;
    private static final String DB_NAME = "QuanLyQuanAn.db";
    int id;


    ListView lstBinhLuan;
    ArrayList<BinhLuan> list2;
    AdapterLSBL AdapterDanhGia;
    ArrayList<BinhLuan> binhLuanList = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getContext() == null) {
            return;
        }

        lstBinhLuan = (ListView) view.findViewById(R.id.listLSBinhLuan);

        list2 = new ArrayList<>();

        AdapterDanhGia = new AdapterLSBL(getContext(), list2);

        lstBinhLuan.setAdapter(AdapterDanhGia);

        //


        //userName = (TextView) findViewById(R.id.username);


        SQLiteDatabase db = Database.initDatabase(getActivity(), DB_NAME);
        Cursor cursor = db.rawQuery("select * from DanhGia where MaND = '" + LoginActivity.MaND + "'", null);


        list2.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int idDG = cursor.getInt(0);
            String Noidung = cursor.getString(1);
            int saoDG = cursor.getInt(2);
            id = idDG;

            byte[] anhBL = cursor.getBlob(3);
            int maND = cursor.getInt(5);

            list2.add(new BinhLuan(idDG, saoDG, Integer.parseInt(LoginActivity.MaND), maND, Noidung, anhBL));
        }
        AdapterDanhGia.notifyDataSetChanged();


    }
    private void delete(int idND) {
        SQLiteDatabase database = Database.initDatabase(getActivity(), DB_NAME);
        database.delete("DanhGia", "MaDG = ?", new String[]{idND + ""});


    }
}
