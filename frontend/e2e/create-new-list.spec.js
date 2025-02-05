// @ts-check
import { test, expect } from '@playwright/test';
import { pageLocators } from './util/locators.js';

test('create new list autonavigates and can be reloaded', async ({ page }) => {
  await page.goto(pageLocators.baseUrl);

  await page.locator(pageLocators.newListButton).click({force: true});

  await page.waitForURL(new RegExp(pageLocators.urlWithUUID));

  await expect(page.locator('h2')).toHaveText(/\'s Shopping List/);
  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("0 items");

  // same assertions hold after a reload, ie data is persisted
  await page.reload();
  await expect(page.locator('h2')).toHaveText(/\'s Shopping List/);
  await expect(page.locator(pageLocators.shoppingListSize)).toHaveText("0 items");
});