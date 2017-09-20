import {Incrementor} from "./incrementor";

export class Transaction{

    bill:number;
    cid:number;
    incrementorHashSet:Set<Incrementor>;


    constructor(cid:number, bill:number, aidSet:Set<Incrementor>) {
        this.bill = bill;
        this.incrementorHashSet = aidSet;
        this.cid = cid;
    }
}