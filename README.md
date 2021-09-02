# Drawable View Pager Indicator

This library generates indicator from the icon you choose. You can customize the icons you choose. Also supports both ViewPager and ViewPager2.

You can create a drawable file from xml or use the imported icons.

![materialdots](https://github.com/emircanpekyurek/DrawableViewPagerIndicator/blob/master/readme_gifs/gif_delete.gif)

![materialdots](https://github.com/emircanpekyurek/DrawableViewPagerIndicator/blob/master/readme_gifs/gif_star.gif)

![materialdots](https://github.com/emircanpekyurek/DrawableViewPagerIndicator/blob/master/readme_gifs/gif_delete.gif)

## Installation

(app) build.gradle:
```gradle
implementation 'com.github.emircanpekyurek:DrawableViewPagerIndicator:1.0.0'
```
#### AND

(root) build.gradle:
```gradle
allprojects {
    repositories {
        ..
        maven { url "https://jitpack.io" }
    }
}
```
or settings.gradle:
```gradle
dependencyResolutionManagement {
    ..
    repositories {
        ..
        maven { url 'https://jitpack.io' }
    }
}
```

## Usage
```xml
<com.pekyurek.emircan.indicator.DrawableViewPagerIndicator
        android:id="@+id/indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:indicator_drawable="@drawable/default_viewpager_indicator"
        app:indicator_width="@dimen/default_indicator_width"
        app:indicator_height="@dimen/default_indicator_height"
        app:indicator_margin="@dimen/default_indicator_margin"
        app:indicator_scale_x="1.5"
        app:indicator_scale_y="1"
        app:indicator_selected_color="@color/default_selected_indicator_color"
        app:indicator_unselected_color="@color/default_unselected_indicator_color" />
```

#### Custom Attributes:
| Attribute | Description |
| --- | --- |
| `indicator_drawable` | indicator drawable (xml or icon)  |
| `indicator_width` | indicator item width |
| `indicator_height` | indicator item height |
| `indicator_margin` | indicator item margin |
| `indicator_scale_x` | selected item scale x |
| `indicator_scale_y` | selected item scale y |
| `indicator_selected_color` | selected item color |
| `indicator_unselected_color` | unselected item color |

#### Code:
```kotlin
    viewPager.adapter = ..... //first set adapter
    indicator.setViewPager(viewPager)
```
