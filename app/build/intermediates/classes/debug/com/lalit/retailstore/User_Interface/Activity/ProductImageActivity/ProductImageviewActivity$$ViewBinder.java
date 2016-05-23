// Generated code from Butter Knife. Do not modify!
package com.lalit.retailstore.User_Interface.Activity.ProductImageActivity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ProductImageviewActivity$$ViewBinder<T extends com.lalit.retailstore.User_Interface.Activity.ProductImageActivity.ProductImageviewActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558521, "field 'imgDetail'");
    target.imgDetail = finder.castView(view, 2131558521, "field 'imgDetail'");
    view = finder.findRequiredView(source, 2131558522, "field 'btnClose'");
    target.btnClose = finder.castView(view, 2131558522, "field 'btnClose'");
  }

  @Override public void unbind(T target) {
    target.imgDetail = null;
    target.btnClose = null;
  }
}
