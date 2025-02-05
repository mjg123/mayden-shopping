// @ts-check
import { test, expect } from '@playwright/test';
import { pageLocators } from './util/locators.js';

// Covers story 4

test('Cross out items on a shopping list', async ({ page }) => {
  await page.goto(pageLocators.baseUrl);
  await page.locator(pageLocators.newListButton).click();
  await page.waitForURL(new RegExp(pageLocators.urlWithUUID));

  const item1 = 'Balloons'
  await page.locator(pageLocators.newItemField).fill(item1);
  await page.locator(pageLocators.addNewItemButton).click();

  const item2 = 'Dog shoes'
  await page.locator(pageLocators.newItemField).fill(item2);
  await page.locator(pageLocators.addNewItemButton).click();

  // cross out the dog shoes
  const checkboxesBefore = await page.locator(pageLocators.toggleItemCheckbox).all();
  await checkboxesBefore[1].click();


  // Assertions: box is now checked and text is struck out
  const checkboxesAfter = await page.locator(pageLocators.toggleItemCheckbox).all();
  await expect(checkboxesAfter[0]).not.toBeChecked();
  await expect(checkboxesAfter[1]).toBeChecked();

  const listTextItems = await page.locator(pageLocators.shoppingListItem).all();
  await expect(listTextItems[0]).not.toHaveClass(/struckOut/);
  await expect(listTextItems[1]).toHaveClass(/struckOut/);
  
  // ...and persisted through a page reload
  await page.reload()
  const checkboxesAfter2 = await page.locator(pageLocators.toggleItemCheckbox).all();
  await expect(checkboxesAfter2[0]).not.toBeChecked();
  await expect(checkboxesAfter2[1]).toBeChecked();

  const listTextItems2 = await page.locator(pageLocators.shoppingListItem).all();
  await expect(listTextItems2[0]).not.toHaveClass(/struckOut/);
  await expect(listTextItems2[1]).toHaveClass(/struckOut/);
});