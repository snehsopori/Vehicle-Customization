"use strict";
var Transaction = (function () {
    function Transaction(cid, bill, aidSet) {
        this.bill = bill;
        this.incrementorHashSet = aidSet;
        this.cid = cid;
    }
    return Transaction;
}());
exports.Transaction = Transaction;
