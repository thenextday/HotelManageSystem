package controllor.ClassPassParameter;

public class DietTrans1_1 {
    private boolean flag;
    private String zhengZe;
    private String sMinPrice;
    private String sMaxPrice;
    private int minPrice;
    private int maxPrice;

    public DietTrans1_1 (String zhengZe,String sMinPrice,String sMaxPrice) {
        this.zhengZe = zhengZe;
        this.sMinPrice = sMinPrice;
        this.sMaxPrice = sMaxPrice;


        if (sMaxPrice.matches(zhengZe) && sMinPrice.matches(zhengZe)) {
            flag = true;

            if (sMaxPrice.equals("")) {
                maxPrice = 0;
            }else {
                maxPrice = Integer.parseInt(sMaxPrice);
            }

//            minPrice = 0;
//            maxPrice = 0;
            if (sMinPrice.equals("")) {
                minPrice = 0;
            } else {
                minPrice = Integer.parseInt(sMinPrice);
            }
        }else {
            flag = false;
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }
}
