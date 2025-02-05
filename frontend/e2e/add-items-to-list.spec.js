// @ts-check
import { test, expect } from '@playwright/test';
import { pageLocators } from './util/locators.js';

// Covers stories 1,2,5
test('add multiple items to a shopping list', async ({ page }) => {
  await page.goto(pageLocators.baseUrl);

  await page.locator(pageLocators.newListButton).click({force: true});

  await page.waitForURL(new RegExp(pageLocators.urlWithUUID));

  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("0 items");

  const item1 = 'Balloons'
  await page.locator(pageLocators.newItemField).fill(item1);
  await page.locator(pageLocators.addNewItemButton).click();
  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("1 item");
  await expect(page.locator(pageLocators.shoppingListItem)).toHaveCount(1);
  await expect(page.locator(pageLocators.newItemField)).toHaveText("");
  await expect(page.locator(pageLocators.shoppingListItem)).toHaveText([item1]);

  const item2 = 'Dog shoes'
  await page.locator(pageLocators.newItemField).fill(item2);
  await page.locator(pageLocators.addNewItemButton).click();
  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("2 items");
  await expect(page.locator(pageLocators.shoppingListItem)).toHaveCount(2);
  await expect(page.locator(pageLocators.newItemField)).toHaveText("");
  await expect(page.locator(pageLocators.shoppingListItem)).toHaveText([item1, item2]);
});