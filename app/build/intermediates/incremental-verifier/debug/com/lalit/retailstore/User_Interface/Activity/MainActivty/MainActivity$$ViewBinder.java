// Generated code from Butter Knife. Do not modify!
package com.lalit.retailstore.User_Interface.Activity.MainActivty;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.lalit.retailstore.User_Interface.Activity.MainActivty.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558514, "field 'tablayout'");
    target.tablayout = finder.castView(view, 2131558514, "field 'tablayout'");
    view = finder.findRequiredView(source, 2131558515, "field 'viewpager'");
    target.viewpager = finder.castView(view, 2131558515, "field 'viewpager'");
    view = finder.findRequiredView(source, 2131558513, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558513, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.tablayout = null;
    target.viewpager = null;
    target.toolbar = null;
  }
}
