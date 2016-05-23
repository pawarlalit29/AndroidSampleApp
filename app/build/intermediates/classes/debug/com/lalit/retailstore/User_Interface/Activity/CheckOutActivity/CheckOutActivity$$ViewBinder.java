// Generated code from Butter Knife. Do not modify!
package com.lalit.retailstore.User_Interface.Activity.CheckOutActivity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CheckOutActivity$$ViewBinder<T extends com.lalit.retailstore.User_Interface.Activity.CheckOutActivity.CheckOutActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558505, "field 'recycleview'");
    target.recycleview = finder.castView(view, 2131558505, "field 'recycleview'");
    view = finder.findRequiredView(source, 2131558513, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558513, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131558507, "field 'txtInfoCount'");
    target.txtInfoCount = finder.castView(view, 2131558507, "field 'txtInfoCount'");
    view = finder.findRequiredView(source, 2131558508, "field 'infoTextPrice'");
    target.infoTextPrice = finder.castView(view, 2131558508, "field 'infoTextPrice'");
  }

  @Override public void unbind(T target) {
    target.recycleview = null;
    target.toolbar = null;
    target.txtInfoCount = null;
    target.infoTextPrice = null;
  }
}
