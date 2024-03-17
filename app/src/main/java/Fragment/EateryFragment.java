package Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.loginform.Activity.LoginActivity;
import com.loginform.Adapter.EateryAdapter;
import com.loginform.Class.Eatery;
import com.loginform.Database.EateryDbHelper;
import com.loginform.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Slide_Images.ImagesSlide;
import Slide_Images.ImagesSlideAdapter;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EateryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EateryFragment extends Fragment {
    EateryDbHelper dbHelper;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private ImagesSlideAdapter imagesSlideAdapter;
    private List<ImagesSlide> imagesSlides;
    private Timer timer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EateryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EateryFragment newInstance(String param1, String param2) {
        EateryFragment fragment = new EateryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbHelper = new EateryDbHelper(context);
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
        return inflater.inflate(R.layout.fragment_eatery, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getContext() == null) {
            return;
        }
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicator);
        imagesSlides = getListImage();
        imagesSlideAdapter = new ImagesSlideAdapter(getContext(), imagesSlides);
        viewPager.setAdapter(imagesSlideAdapter);
        circleIndicator.setViewPager(viewPager);
        imagesSlideAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlideImage();

        EateryDbHelper dbHelper = new EateryDbHelper(getContext());
        List<Eatery> eateryList = dbHelper.getAllEatery();
        TextView id = view.findViewById(R.id.txtMaND);

        id.setText(LoginActivity.MaND);
        // Khởi tạo RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.eatery_recycler_view);
        int numberOfColumns = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);

        // Thiết lập adapter cho RecyclerView
        EateryAdapter adapter = new EateryAdapter(eateryList, getContext());
        recyclerView.setAdapter(adapter);

        SearchView searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lý tìm kiếm khi người dùng nhấn nút tìm kiếm trên bàn phím
                query = searchView.getQuery().toString();
                List<Eatery> eateryList = dbHelper.searchEateryByName(query);

                // Khởi tạo RecyclerView
                RecyclerView recyclerView = view.findViewById(R.id.eatery_recycler_view);
                int numberOfColumns = 2;
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numberOfColumns);
                recyclerView.setLayoutManager(layoutManager);

                // Thiết lập adapter cho RecyclerView
                EateryAdapter adapter = new EateryAdapter(eateryList, getContext());
                recyclerView.setAdapter(adapter);
                SearchView searchView = view.findViewById(R.id.searchView);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý tìm kiếm khi người dùng nhập từ khóa tìm kiếm
                if (TextUtils.isEmpty(newText)) {
                    EateryDbHelper dbHelper = new EateryDbHelper(getContext());
                    List<Eatery> eateryList = dbHelper.getAllEatery();

                    // Khởi tạo RecyclerView
                    RecyclerView recyclerView = view.findViewById(R.id.eatery_recycler_view);
                    int numberOfColumns = 2;
                    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numberOfColumns);
                    recyclerView.setLayoutManager(layoutManager);

                    // Thiết lập adapter cho RecyclerView
                    EateryAdapter adapter = new EateryAdapter(eateryList, getContext());
                    recyclerView.setAdapter(adapter);
                }
                return false;
            }

        });

    }
    private void autoSlideImage() {
        if(imagesSlides == null || imagesSlides.isEmpty() || viewPager == null){
            return;
        }
        if(timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = imagesSlides.size() - 1;
                        if(currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);

                        }else{
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    private List<ImagesSlide> getListImage(){
        List<ImagesSlide> list = new ArrayList<>();
        list.add(new ImagesSlide(R.drawable.hinh1));
        list.add(new ImagesSlide(R.drawable.hinh2));
        list.add(new ImagesSlide(R.drawable.hinh6));
        list.add(new ImagesSlide(R.drawable.hinh7));
        return list;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

}








