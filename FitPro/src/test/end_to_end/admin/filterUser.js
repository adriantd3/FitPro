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

    page.locator('input[name="mail"]').type('david@gmail.com');
    page.locator('input[name="password"]').type('david');

    const loginButton = page.locator('button[name="login"]');
    sleep(3);
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), loginButton.click()]);

    const userButton = page.locator('button[name="user-menu"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), userButton.click()]);

    page.locator('input[name="nombre"]').type('david');

    sleep(1);

    const filterButton = page.locator('button[name="filter"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), filterButton.click()]);

    const afterLenght = page.$$('#users tbody tr').length;

    check(page, {
      'User filtered': afterLenght == 1,
    });

  } finally {
    page.close();
  }
}