package model.table;

public class Restaurant {
    private Integer restaurantId;
    private String restaurantName;
    private Float restaurantPrice;

    public Restaurant() {
    }

    public Restaurant(int restaurantId, String restaurantName, float restaurantPrice) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantPrice = restaurantPrice;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public float getRestaurantPrice() {
        return restaurantPrice;
    }

    public void setRestaurantPrice(float restaurantPrice) {
        this.restaurantPrice = restaurantPrice;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantPrice=" + restaurantPrice +
                '}';
    }
}
