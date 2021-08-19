package com.example.mylibrary.tool;

/**
 * @作者 邸昌顺
 * @时间 2019/3/4 13:26
 * @描述
 */
public class UICViewTool {



    public static class HRecyclerViewBean{

        private float itemCount;//屏幕中展示的单元个数，可以是半个，1/3个这种数据形式

        public int itemSumCount;//横向展示的item总个数
        public int leftMargin;
        public int itemWidth;
        public int itemHeight;
        public int itemDecorationWidth;

        private float W2H;

        public HRecyclerViewBean(float showItemCount, int leftMargin, int itemSumCount, float W2H) {
            this.itemCount = showItemCount;
            this.leftMargin = leftMargin;
            this.itemSumCount = itemSumCount;
            this.W2H = W2H;
            itemDecorationWidth = UICDisplayTool.dp2Px(18);//默认间隔
            measureHRecyclerViewWithMargin();
        }

        private void measureHRecyclerViewWithMargin(){
            int decorationCount = (int) Math.floor(itemCount);
            int usableWidth = (int) (UICScreenTool.getScreenWidth() - leftMargin - decorationCount * itemDecorationWidth - itemCount * UICDisplayTool.dp2Px(12));
            itemWidth = (int) (usableWidth / itemCount);
            itemHeight = (int) (itemWidth / W2H);
        }

        @Override
        public String toString() {
            return "HRecyclerViewBean{" +
                    "itemCount=" + itemCount +
                    ", itemSumCount=" + itemSumCount +
                    ", leftMargin=" + leftMargin +
                    ", itemWidth=" + itemWidth +
                    ", itemHeight=" + itemHeight +
                    ", itemDecorationWidth=" + itemDecorationWidth +
                    '}';
        }
    }

}
