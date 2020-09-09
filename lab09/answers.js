//question 3.1
var cursor = db.staging.aggregate([{$replaceRoot: {newRoot: "$customer"}}])

while (cursor.hasNext()) {
    db.customers.insertOne(cursor.next());
}


//question 3.2
var cursor = db.staging.aggregate([{$replaceRoot: {newRoot: "$user"}}])

while (cursor.hasNext()) {
    db.users.insertOne(cursor.next());
}