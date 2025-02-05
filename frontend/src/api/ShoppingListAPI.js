const apiGET = (path) => async (cb, id) => {
  try {
    const resp = await fetch(path + "/" + id);
    if (!resp.ok) {
      if (resp.status === 404) {
        cb(null);
        return;
      }
      throw new Error(`Response status: ${resp.status}`);
    }
    const json = await resp.json();
    cb(json);
  } catch (error) {
    console.error(error.message);
  }
}

const apiPOST = (path) => async (cb, body) => {
  try {
    const resp = await fetch(path,
      {
        "method": "POST",
        "body": JSON.stringify(body),
        "headers": { "content-type": "application/json" }
      });
    if (!resp.ok) {
      throw new Error(`Response status: ${resp.status}`);
    }
    const json = await resp.json();
    cb(json);
  } catch (error) {
    console.error(error.message);
  }
}

const apiPUT = (path) => async (cb, body) => {
  try {
    const resp = await fetch(path,
      {
        "method": "PUT",
        "body": JSON.stringify(body),
        "headers": { "content-type": "application/json" }
      });
    if (!resp.ok) {
      throw new Error(`Response status: ${resp.status}`);
    }
    const json = await resp.json();
    cb(json);
  } catch (error) {
    console.error(error.message);
  }
}

const apiDELETE = (path) => async () => {
  try {
    const resp = await fetch(path,
      { "method": "DELETE" });
    if (!resp.ok) {
      throw new Error(`Response status: ${resp.status}`);
    }
  } catch (error) {
    console.error(error.message);
  }
}

const randomItemName = () => {

  const items = [
    "Candles for Matthew's birthday cake",
    "A ridiculously large bag of popcorn",
    "Glittery wrapping paper",
    "'Secret' ingredients for grandma's recipe",
    "Fancy chocolate that I'll pretend is a gift",
    "Batteries for the remote I keep losing",
    "Bubble wrap (not needed, but fun)",
    "Extra spicy hot sauce 🔥🔥🔥",
    "Rubber duck",
    "Marshmallows for an epic hot chocolate",
    "A tiny flower in a pot (maybe watering can too?)",
    "Glow-in-the-dark ceiling stars",
    "A suspiciously large amount of wotsits",
    "Socks with funny dog faces on them",
    "Notebook for world domination plans",
    "Scented candles that smell like 'mystery forest'",
    "A toy for my neice (it's really for me)",
    "A giant jar of pickles",
    "Confetti for the surprise party",
    "A single lemon, for dramatic effect"];

  return items[Math.floor(Math.random() * items.length)];
}

const addItemToList = (cb, listId, itemName) => {
  apiPOST("/api/list/" + listId + "/item")(cb, { name: itemName })
}

const removeItemFromList = (cb, listId, itemIndex) => {
  apiDELETE("/api/list/" + listId + "/item/" + itemIndex)()
    .then(apiGET("/api/list")(cb, listId));
}

const toggleItemStruckOut = (cb, listId, itemIndex, item) => {
  const newItem = {...item};
  newItem.struckOut = !item.struckOut;
  apiPUT("/api/list/" + listId + "/item/" + itemIndex)(cb, newItem)
}

const ShoppingListAPI = {
  randomItemName: randomItemName,
  fetchList: apiGET("/api/list"),
  createNewShoppingList: apiPOST("/api/list"),
  addItemToList: addItemToList,
  removeItemFromList: removeItemFromList,
  toggleItemStruckOut: toggleItemStruckOut
}

export { ShoppingListAPI };
