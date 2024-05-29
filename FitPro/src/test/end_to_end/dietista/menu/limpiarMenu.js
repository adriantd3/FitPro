import { browser } from 'k6/experimental/browser';
import { check, sleep } from 'k6';

export const options = {
  scenarios: {
    ui: {
      executor: 'shared-iterations',
      options: {
        browser: {
          type: 'chromium',
        },
      },
    } 
  },
  thresholds: {
    checks: ["rate==1.0"]
  }
}

export default async function () {
  const page = browser.newPage();
  try {
    await page.goto('http://localhost:8080/');

    page.locator('input[name="mail"]').clear();
    page.locator('input[name="password"]').clear();

    page.locator('input[name="mail"]').type('irene@gmail.com');
    page.locator('input[name="password"]').type('irene');

    const loginButton = page.locator('button[name="login"]');
    sleep(0.5);
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), loginButton.click()]);

    const menuButton = page.locator('button[name="menus"]');
    sleep(0.5);
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), menuButton.click()]);
    
    
    const lastMenuIndex = page.$$('table[name="menu_table"] tbody tr').length;
    page.$('table[name="menu_table"] tbody tr[name="menu'+(lastMenuIndex)+'"]').scrollIntoViewIfNeeded();

    let menu = page.$('table[name="menu_table"] tbody tr[name="menu'+(lastMenuIndex)+'"]')
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), menu.click()]);
    sleep(0.5);
    
    const limpiarMenuButton = page.locator('button[name="limpiar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), limpiarMenuButton.click()]);
    
    const actualName = page.$('input[name="nombreMenu"]').inputValue().trim();
    
    check(page, {
      'MenuName': p => actualName === "",
  });

  } finally {
    page.close();
  }
}