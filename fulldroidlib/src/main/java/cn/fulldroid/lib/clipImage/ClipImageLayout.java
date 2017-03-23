package cn.fulldroid.lib.clipImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import cn.fulldroid.lib.utils.AppUtil;
import cn.fulldroid.lib.utils.ImageLoader;

public class ClipImageLayout extends RelativeLayout
{

	private ClipZoomImageView mZoomImageView;
	private ClipImageBorderView mClipImageView;
	private Drawable mDrawable ;
	/**
	 * 这里测试，直接写死了大小，真正使用过程中，可以提取为自定义属性
	 */
	private int mHorizontalPadding = 20;
	private float scale;
	private boolean isCustom = false;
	private Context mContext ;
	public ClipImageLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mContext = context ;
		mZoomImageView = new ClipZoomImageView(context);
		mClipImageView = new ClipImageBorderView(context);
	}

	/**
	 * 对外公布设置边距的方法,单位为dp
	 * 
	 * @param mHorizontalPadding
	 */
	public void setHorizontalPadding(int mHorizontalPadding)
	{
		this.mHorizontalPadding = mHorizontalPadding;
	}

	public void setCustom(boolean custom) {
		isCustom = custom;
	}

	public void setImage(String imagePath){
		android.view.ViewGroup.LayoutParams lp = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);


		this.addView(mZoomImageView, lp);
		this.addView(mClipImageView, lp);

		// 计算padding的px
		mHorizontalPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, mHorizontalPadding, getResources()
						.getDisplayMetrics());
		mZoomImageView.setHorizontalPadding(mHorizontalPadding);
		mClipImageView.setHorizontalPadding(mHorizontalPadding);
		if(isCustom){
			Log.e("scale","边框设置"+scale +"");
			mZoomImageView.setCustomScale(scale);
			mClipImageView.setCustomBorder(scale);
		}
		ImageLoader.ImageSize imageSize = new ImageLoader.ImageSize();
		imageSize.width = AppUtil.getScreenWidth(mContext)/2;
		imageSize.height = AppUtil.getScreenHeight(mContext)/2;
		ImageLoader.getInstance().loadImage(imagePath,mZoomImageView,imageSize);
	}
	/**
	 * 裁切图片
	 * 
	 * @return
	 */
	public Bitmap clip()
	{
		return mZoomImageView.clip();
	}
	public void setImageScale(float scale){
		this.scale = scale;
	}
}
