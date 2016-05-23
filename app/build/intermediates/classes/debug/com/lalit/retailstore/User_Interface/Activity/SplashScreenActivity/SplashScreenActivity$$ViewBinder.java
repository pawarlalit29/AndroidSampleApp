// Generated code from Butter Knife. Do not modify!
package com.lalit.retailstore.User_Interface.Activity.SplashScreenActivity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SplashScreenActivity$$ViewBinder<T extends com.lalit.retailstore.User_Interface.Activity.SplashScreenActivity.SplashScreenActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558523, "field 'kenBurnsImages'");
    target.kenBurnsImages = finder.castView(view, 2131558523, "field 'kenBurnsImages'");
    view = finder.findRequiredView(source, 2131558525, "field 'welcomeText2'");
    target.welcomeText2 = finder.castView(view, 2131558525, "field 'welcomeText2'");
    view = finder.findRequiredView(source, 2131558524, "field 'lvSp'");
    target.lvSp = finder.castView(view, 2131558524, "field 'lvSp'");
  }

  @Override public void unbind(T target) {
    target.kenBurnsImages = null;
    target.welcomeText2 = null;
    target.lvSp = null;
  }
}
