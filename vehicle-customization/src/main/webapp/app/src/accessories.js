"use strict";
var Accessories = (function () {
    function Accessories(name, price, image) {
        this.accessoryName = "";
        this.accessoryPrice = 0;
        this.image = "";
        this.accessoryName = name;
        this.accessoryPrice = price;
        this.image = image;
    }
    return Accessories;
}());
exports.Accessories = Accessories;
