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
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), loginButton.click()]);

    sleep(1);

    const userButton = page.locator('button[name="user-menu"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), userButton.click()]);
    sleep(1);

    const expectedLenght = page.$$('#users tbody tr').length-1;
    page.$('#users tbody tr[name="user'+(expectedLenght)+'"]').scrollIntoViewIfNeeded();

    const user = page.$('#users tbody tr[name="user'+(expectedLenght)+'"]')
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), user.click()]);
    sleep(0.5);

    page.locator('input[name="Apellidos"]').clear();
    page.locator('input[name="Apellidos"]').type('carmona');

    sleep(1);

    const createButton = page.locator('button[name="save-user"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), createButton.click()]);

    check(page, {
        'User edited': p => p.locator('#users tbody tr[name="user'+(expectedLenght)+'"] td[name="apellido"]').textContent().trim() == 'carmona',
      });


  } finally {
    page.close();
  }
}