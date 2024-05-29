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

    sleep(0.5);

    const assingButton = page.locator('button[name="assing-button"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), assingButton.click()]);

    sleep(0.5);

    const expectedClientLenght = page.$$('#clients tbody tr').length-1;
    page.$('#clients tbody tr[name="client'+(expectedClientLenght)+'"]').scrollIntoViewIfNeeded();

    const client = page.$('#clients tbody tr[name="client'+(expectedClientLenght)+'"]')
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), client.click()]);

    sleep(0.5);

    const BeforeAssingLenght = page.$$('#assigned tbody tr').length-1;
    page.$('#assigned tbody tr[name="aWorker'+(BeforeAssingLenght)+'"]').scrollIntoViewIfNeeded();

    const aWorker = page.$('#assigned tbody tr[name="aWorker'+(BeforeAssingLenght)+'"]')
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), aWorker.click()]);

    sleep(0.5);

    const unAssingButton = page.locator('button[name="unAssign"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), unAssingButton.click()]);

    const AfterAssingLenght = page.$$('#assigned tbody tr').length-1;

    check(page, {
        'User edited': BeforeAssingLenght == AfterAssingLenght + 1,
      });


  } finally {
    page.close();
  }
}