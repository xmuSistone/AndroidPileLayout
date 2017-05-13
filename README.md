# android-pile-layout
An abnormal horizontal ListView-like pile layout.

### captured images
<img src="capture/capture1.gif" width="360" height="645"/> <img src="capture/capture2.gif" width="360" height="645"/> 

### how to use
#### 1. decleare PileLayout in your xml file
```xml
<com.stone.pile.libs.PileLayout
        android:id="@+id/pileLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:paddingBottom="5dp"
        android:paddingTop="5dp"
        pile:interval="10dp"
        pile:scaleStep="0.22"
        pile:widthHeightRate="1.22" />
```
Meanwhile, pileLayout is able to be customized by these three params:

|name|format|description|
|:---:|:---:|:---:|
| interval | dimension |items-margin each other
| sizeRatio | float |each item's height/witdth
| scaleStep | float |size scale step when needed

```java
pileLayout = (PileLayout) findViewById(R.id.pileLayout);
pileLayout.setAdapter(new PileLayout.Adapter() {
            @Override
            public int getLayoutId() {
                // item's layout resource id
                return R.layout.item_layout;
            }

            @Override
            public void bindView(View view, int position) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (viewHolder == null) {
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
                }
                // recycled view bind new position
            }

            @Override
            public int getItemCount() {
                // item count
                return dataList.size();
            }

            @Override
            public void displaying(int position) {
                // right displaying the left biggest itemView's position 
            }

            @Override
            public void onItemClick(View view, int position) {
                // on item click
            }
});
```

### demo apk
[download](capture/app-debug.apk)

## License

    Copyright 2017, xmuSistone

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
