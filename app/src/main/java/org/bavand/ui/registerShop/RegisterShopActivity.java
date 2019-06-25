package org.bavand.ui.registerShop;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.bavand.R;
import org.bavand.helper.Transitions;

import java.io.ByteArrayOutputStream;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterShopActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.imageViewAnim)
    ImageView imageViewAnim;
    @BindView(R.id.imageButtonClose)
    ImageButton imageButtonClose;
    @BindView(R.id.imageButtonPic1)
    ImageButton imageButtonPic1;
    @BindView(R.id.imageButtonPic2)
    ImageButton imageButtonPic2;
    @BindView(R.id.imageButtonPic3)
    ImageButton imageButtonPic3;
    @BindView(R.id.editeTextShopTitle)
    EditText editTextShopTitle;
    @BindView(R.id.editeTextShopManager)
    EditText editTextShopManager;
    @BindView(R.id.editeTextMobile)
    EditText editTextMobile;
    @BindView(R.id.editeTextTelephone)
    EditText editTextTelephone;
    @BindView(R.id.editeTextWebSite)
    EditText editTextWebSite;
    @BindView(R.id.editeTextTelegram)
    EditText editTextTelegram;
    @BindView(R.id.editeTextComment)
    EditText editTextComment;
    @BindView(R.id.editeTextAddress)
    EditText editTextAddress;
    @BindView(R.id.spinnerCategories)
    Spinner spinnerCategories;
    @BindView(R.id.checkboxOffCode)
    CheckBox checkBoxOffCode;
    @BindView(R.id.checkboxOrderByPhone)
    CheckBox checkBoxOrderByPhone;
    @BindView(R.id.buttonRegisterShop)
    Button buttonRegisterShop;
    private String pic1, pic2, pic3, shopTitle, shopManager, mobile, telephone, webSite, telegram, comment, address, categories;
    private boolean isHaveOffCode, isHaveOrderByPhone;
    private int PICK_IMAGE_REQUEST;

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister_shop);
        Transitions.setupEnterExitWindowAnimations(this);
        //MainActivity.changeStatusBarColor(this);
        ButterKnife.bind(this);
        setupView();
        //overridePendingTransition(R.anim.slide_in_up,0);
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }

    private void setupView() {
        imageButtonPic1.setOnClickListener(this);
        imageButtonPic2.setOnClickListener(this);
        imageButtonPic3.setOnClickListener(this);
        imageButtonClose.setOnClickListener(this);
        Glide.with(this)
                .load(R.drawable.adventurer)
                .into(imageViewAnim);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (PICK_IMAGE_REQUEST == 1)
            startActivityForResult(Intent.createChooser(intent, "انتخاب تصویر اول"), PICK_IMAGE_REQUEST);
        else if (PICK_IMAGE_REQUEST == 2)
            startActivityForResult(Intent.createChooser(intent, "انتخاب تصویر دوم"), PICK_IMAGE_REQUEST);
        else if (PICK_IMAGE_REQUEST == 3)
            startActivityForResult(Intent.createChooser(intent, "انتخاب تصویر سوم"), PICK_IMAGE_REQUEST);
    }

    private void showCamera() {
        Intent intent = new Intent();
        intent.setType("image/*");
        if (PICK_IMAGE_REQUEST == 1)
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), PICK_IMAGE_REQUEST);
        else if (PICK_IMAGE_REQUEST == 2)
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), PICK_IMAGE_REQUEST);
        else if (PICK_IMAGE_REQUEST == 3)
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), PICK_IMAGE_REQUEST);
    }

    private void askForChoosePicture() {
        TextView textViewchooseFromGallery;
        TextView textViewTakePicture;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_choose_image);
        dialog.setCancelable(true);
        textViewchooseFromGallery = dialog.findViewById(R.id.textViewChooseFromGallery);
        textViewTakePicture = dialog.findViewById(R.id.textViewTakePicture);
        textViewchooseFromGallery.setOnClickListener(v -> {
            showFileChooser();
        });
        textViewTakePicture.setOnClickListener(v -> {
            showCamera();
        });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonPic1:
                PICK_IMAGE_REQUEST = 1;
                askForChoosePicture();
                break;
            case R.id.imageButtonPic2:
                PICK_IMAGE_REQUEST = 2;
                askForChoosePicture();
                break;
            case R.id.imageButtonPic3:
                PICK_IMAGE_REQUEST = 3;
                askForChoosePicture();
                break;
            case R.id.imageButtonClose:
                onBackPressed();
        }
    }

}
