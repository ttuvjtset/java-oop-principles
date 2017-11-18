package task09_strategies;

class TankView {

    String getJSON (Tank tank) {
        return  "{\n price: " + tank.getPrice(10) + "\n" + "id: " + tank.getId() + "}";
    }

}
