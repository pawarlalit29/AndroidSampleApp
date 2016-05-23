// Generated code from Butter Knife. Do not modify!
package com.lalit.retailstore.User_Interface.Activity.ProductDetailActivity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ProductDetailActivity$$ViewBinder<T extends com.lalit.retailstore.User_Interface.Activity.ProductDetailActivity.ProductDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558517, "field 'infoImage' and method 'onInfoImageView'");
    target.infoImage = finder.castView(view, 2131558517, "field 'infoImage'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onInfoImageView();
        }
      });
    view = finder.findRequiredView(source, 2131558513, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558513, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131558520, "field 'fabAdd' and method 'addORremove'");
    target.fabAdd = finder.castView(view, 2131558520, "field 'fabAdd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.addORremove();
        }
      });
    view = finder.findRequiredView(source, 2131558516, "field 'collapsingToolbar'");
    target.collapsingToolbar = finder.castView(view, 2131558516, "field 'collapsingToolbar'");
    view = finder.findRequiredView(source, 2131558519, "field 'infoTextDesc'");
    target.infoTextDesc = finder.castView(view, 2131558519, "field 'infoTextDesc'");
    view = finder.findRequiredView(source, 2131558518, "field 'infoTextTitle'");
    target.infoTextTitle = finder.castView(view, 2131558518, "field 'infoTextTitle'");
  }

  @Override public void unbind(T target) {
    target.infoImage = null;
    target.toolbar = null;
    target.fabAdd = null;
    target.collapsingToolbar = null;
    target.infoTextDesc = null;
    target.infoTextTitle = null;
  }
}
