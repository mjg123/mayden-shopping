import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';

import { ShoppingListAPI } from '../api/ShoppingListAPI.js'

export function ShoppingList({ shoppingList, setShoppingList }) {

    let { id } = useParams();

    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        setLoading(true);
        ShoppingListAPI.fetchList(setShoppingList, id)
            .then(() => {
                setLoading(false);
            });
    }, []);

    return (
        <div id="shoppingList">

            {isLoading ? <span>...</span> :

                (shoppingList ?
                    <DisplayShoppingList shoppingList={shoppingList} setShoppingList={setShoppingList} />
                    :
                    <span>
                        This shopping list no longer exists
                    </span>
                )
            }

        </div >)
}

function DisplayShoppingList({ shoppingList, setShoppingList }) {

    const itemComponents = shoppingList.items.map((item, idx) =>
        <ShoppingListItem key={"item-" + idx}
            item={item} itemIndex={idx}
            shoppingList={shoppingList} setShoppingList={setShoppingList} />
    )

    return (<>
        <span className="debug">
            {JSON.stringify(shoppingList)}
        </span>
        <div>
            <h2>🛒 {shoppingList.name}</h2>
            <div id="shopping-list-size" className="info">
                {shoppingList.items.length} {shoppingList.items.length == 1 ? "item" : "items"}
            </div>
            <div id="listContainer">
                <ul className="shoppingList cols-both">{itemComponents}</ul>
                <AddItem shoppingList={shoppingList} setShoppingList={setShoppingList} />
            </div>
        </div>
    </>);
}

function ShoppingListItem({ item, shoppingList, setShoppingList, itemIndex }) {
    return (
        <li className="listItem">
            <div className="listItemName">{item.name}</div>
            <div className="listItemRemove">
                <button className="input removeItemButton" onClick={() => {
                    ShoppingListAPI.removeItemFromList((newList) => {
                        setShoppingList(newList);
                    }, shoppingList.id, itemIndex);
                }}>🗑️ remove</button>
            </div>
        </li>)
}

function AddItem({ shoppingList, setShoppingList }) {

    const [newItem, setNewItem] = useState("");

    return (
        <>
            <input id="new-item-text" type="text" className="input cols-left"
                placeholder={ShoppingListAPI.randomItemName()}
                value={newItem} onChange={e => setNewItem(e.target.value)} />
            <button id="add-new-item" disabled={!newItem} className="input cols-right"
                onClick={() => {
                    ShoppingListAPI.addItemToList(newList => {
                        setShoppingList(newList);
                        setNewItem("");
                    }, shoppingList.id, newItem);
                }}>➕ add to list</button>
        </>
    );
}