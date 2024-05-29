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
    
    
    page.locator('input[name="nombreDieta"]').clear();
    page.locator('input[name="nombreDieta"]').type('DietaTest');
    sleep(0.5);
    
    let saveDietaButton = page.locator('button[name="guardar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), saveDietaButton.click()]);
    
    const lastDietaIndex = page.$$('table[name="dieta_table"] tbody tr').length;
    page.$('table[name="dieta_table"] tbody tr[name="dieta'+(lastDietaIndex)+'"]').scrollIntoViewIfNeeded();

    let dieta = page.$('table[name="dieta_table"] tbody tr[name="dieta'+(lastDietaIndex)+'"]')
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), dieta.click()]);
    sleep(0.5);
    
    page.locator('input[name="nombreDieta"]').clear();
    page.locator('input[name="nombreDieta"]').type('NewName');
    sleep(0.5);

    saveDietaButton = page.locator('button[name="guardar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), saveDietaButton.click()]);

    dieta = page.$('table[name="dieta_table"] tbody tr[name="dieta'+(lastDietaIndex)+'"]')
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), dieta.click()]);
    sleep(0.5);
    const actualName = page.$('input[name="nombreDieta"]').inputValue().trim();

    const deleteDietaButton = page.locator('button[name="eliminar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), deleteDietaButton.click()]);
    
    check(page, {
      'DietaName': p => actualName === "NewName",
  });

  } finally {
    page.close();
  }
}