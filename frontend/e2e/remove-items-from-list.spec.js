// @ts-check
import { test, expect } from '@playwright/test';
import { pageLocators } from './util/locators.js';

// Covers story 3
test('add then remove multiple items to a shopping list', async ({ page }) => {
  await page.goto(pageLocators.baseUrl);
  await page.locator(pageLocators.newListButton).click();
  await page.waitForURL(new RegExp(pageLocators.urlWithUUID));

  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("0 items");

  const item1 = 'Balloons'
  await page.locator(pageLocators.newItemField).fill(item1);
  await page.locator(pageLocators.addNewItemButton).click();

  const item2 = 'Dog shoes'
  await page.locator(pageLocators.newItemField).fill(item2);
  await page.locator(pageLocators.addNewItemButton).click();

  const item3 = 'More balloons'
  await page.locator(pageLocators.newItemField).fill(item3);
  await page.locator(pageLocators.addNewItemButton).click();

  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("3 items");
  await expect(page.locator(pageLocators.shoppingListItem)).toHaveText([item1, item2, item3]);

  // remove middle item
  const buttons3 = await page.locator(pageLocators.removeItemButton).all();
  await buttons3[1].click();
  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("2 items");
  await expect(page.locator(pageLocators.shoppingListItem)).toHaveText([item1, item3]);

  // remove first item
  const buttons2 = await page.locator(pageLocators.removeItemButton).all();
  await buttons2[0].click();
  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("1 item");
  await expect(page.locator(pageLocators.shoppingListItem)).toHaveText([item3]);

  // this is all persisted
  await page.reload()
  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("1 item");
  await expect(page.locator(pageLocators.shoppingListItem)).toHaveText([item3]);

});