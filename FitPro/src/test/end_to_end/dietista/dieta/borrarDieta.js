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

    const menuButton = page.locator('button[name="dietas"]');
    sleep(0.5);
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), menuButton.click()]);
    
    
    page.locator('input[name="nombreDieta"]').clear();
    page.locator('input[name="nombreDieta"]').type('DietaTest');
    sleep(0.5);
    
    const saveDietaButton = page.locator('button[name="guardar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), saveDietaButton.click()]);
    
    const expectedLenght = page.$$('table[name="dieta_table"] tbody tr').length-1;
    page.$('table[name="dieta_table"] tbody tr[name="dieta'+(expectedLenght+1)+'"]').scrollIntoViewIfNeeded();

    const dieta = page.$('table[name="dieta_table"] tbody tr[name="dieta'+(expectedLenght+1)+'"]')
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), dieta.click()]);
    sleep(0.5);
    
    const deleteDietaButton = page.locator('button[name="eliminar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), deleteDietaButton.click()]);
    

    const actualLenght = page.$$('table[name="dieta_table"] tbody tr').length;
    
    check(page, {
      'DietasLenght': p => actualLenght === expectedLenght,
  });

  } finally {
    page.close();
  }
}