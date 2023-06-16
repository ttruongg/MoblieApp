package com.example.mobileapp.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mobileapp.ProductDetailActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.ViewpageActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ImageView imgPhone;
    public ImageView imgLaptop;
    public ImageView imgSound;
    public ImageView imgOther;

    ImageView imgItem1;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imgPhone = (ImageView) view.findViewById(R.id.imageView_MobileCategory);
        imgLaptop = (ImageView) view.findViewById(R.id.imageView_LaptopCategory);
        imgSound = (ImageView) view.findViewById(R.id.imageView_SoundCategory);
        imgOther = (ImageView) view.findViewById(R.id.imageView_OtherCategory);


        //laptop category
        imgLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPhone = new Intent(getActivity(), ViewpageActivity.class);
                String catid = "2";
//                Chỗ này làm sao để lấy user_id từ login
//                intentPhone.putExtra("user_id", id);
                intentPhone.putExtra("category_id", catid);
                startActivity(intentPhone);
                Intent intent = new Intent(getActivity(), ViewpageActivity.class);
                startActivity(intent);
            }
        });
        //phone category
        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPhone = new Intent(getActivity(), ViewpageActivity.class);
                String catid = "2";
//                Chỗ này làm sao để lấy user_id từ login
//                intentPhone.putExtra("user_id", id);
                intentPhone.putExtra("category_id", catid);
                startActivity(intentPhone);
                Intent intent = new Intent(getActivity(), ViewpageActivity.class);
                startActivity(intent);
            }
        });
        //sound category
        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPhone = new Intent(getActivity(), ViewpageActivity.class);
                String catid = "3";
//                Chỗ này làm sao để lấy user_id từ login
//                intentPhone.putExtra("user_id", id);
                intentPhone.putExtra("category_id", catid);
                startActivity(intentPhone);
                Intent intent = new Intent(getActivity(), ViewpageActivity.class);
                startActivity(intent);
            }
        });
        //other category
        imgOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPhone = new Intent(getActivity(), ViewpageActivity.class);
                String catid = "4";
//                Chỗ này làm sao để lấy user_id từ login
//                intentPhone.putExtra("user_id", id);
                intentPhone.putExtra("category_id", catid);
                startActivity(intentPhone);
                Intent intent = new Intent(getActivity(), ViewpageActivity.class);
                startActivity(intent);
            }
        });

        imgItem1 = (ImageView) view.findViewById(R.id.imageView_NewItem_1_Img);
        imgItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}