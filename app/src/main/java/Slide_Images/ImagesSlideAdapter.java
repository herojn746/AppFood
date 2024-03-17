package Slide_Images;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.loginform.R;

import java.util.List;

public class ImagesSlideAdapter extends PagerAdapter {
    private Context mContext;
    private List<ImagesSlide> imagesSlideList;

    public ImagesSlideAdapter(Context mContext, List<ImagesSlide> imagesSlideList) {
        this.mContext = mContext;
        this.imagesSlideList = imagesSlideList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide_images, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        ImagesSlide imagesSlide = imagesSlideList.get(position);
        if(imagesSlide != null){
            Glide.with(mContext).load(imagesSlide.getImageResourceId()).into(imageView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if(imagesSlideList != null){
            return imagesSlideList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
