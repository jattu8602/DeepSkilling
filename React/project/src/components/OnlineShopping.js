import React from 'react';

const cartItems = [
  { Itemname: 'Laptop', Price: 800 },
  { Itemname: 'Mouse', Price: 25 },
  { Itemname: 'Keyboard', Price: 50 },
  { Itemname: 'Monitor', Price: 300 },
  { Itemname: 'Headphones', Price: 100 }
];

function Cart({ item }) {
  return (
    <div className="box">
      <p>{item.Itemname}: ${item.Price}</p>
    </div>
  );
}

function OnlineShopping() {
  return (
    <div>
      <h3>Online Shopping Cart</h3>
      {cartItems.map((item, i) => <Cart key={i} item={item} />)}
    </div>
  );
}

export default OnlineShopping;
