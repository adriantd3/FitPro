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

    const dietaButton = page.locator('button[name="dietas"]');
    sleep(0.5);
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), dietaButton.click()]);
    
    
    const lastDietaIndex = page.$$('table[name="dieta_table"] tbody tr').length;
    page.$('table[name="dieta_table"] tbody tr[name="dieta'+(lastDietaIndex)+'"]').scrollIntoViewIfNeeded();

    let dieta = page.$('table[name="dieta_table"] tbody tr[name="dieta'+(lastDietaIndex)+'"]')
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), dieta.click()]);
    sleep(0.5);
    
    const limpiarDietaButton = page.locator('button[name="limpiar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), limpiarDietaButton.click()]);
    
    const actualName = page.$('input[name="nombreDieta"]').inputValue().trim();
    
    check(page, {
      'DietaName': p => actualName === "",
  });

  } finally {
    page.close();
  }
}