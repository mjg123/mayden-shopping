// @ts-check
import { test, expect } from '@playwright/test';
import { pageLocators } from './util/locators.js';

test('homepage loads', async ({ page }) => {
  await page.goto(pageLocators.baseUrl);
  await expect(page).toHaveTitle(/Trolley Time/);
  await expect(page.locator(pageLocators.shoppingList)).toHaveCount(0);
});