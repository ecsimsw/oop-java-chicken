package domain;

public class Table {
    private final int number;
    private int orderAmount = 0;

    private Orders orders = null;

    public Table(final int number) {
        this.number = number;
    }

    public boolean isNumber(int number) {
        return this.number == number;
    }

    public void addOrder(Menu menu, int count) {
        if (orders == null) {
            orders = new Orders();
        }
        updateOrderAmount(menu, count);
        orders.addOrder(menu, count);
    }

    private void updateOrderAmount(Menu menu, int count) {
        orderAmount += (menu.getPrice() * count);
    }

    public boolean isEmpty() {
        return orders == null;
    }

    public void makeTableEmpty() {
        orders = null;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public int getOrderQuantityInCategory(Category category) {
        return orders.getNumberOfCategory(category);
    }

    public String getBill() {
        return orders.toString();
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
