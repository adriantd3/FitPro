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
    
    const saveDietaButton = page.locator('button[name="guardar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), saveDietaButton.click()]);
    
    const expectedLenght = page.$$('table[name="dieta_table"] tbody tr').length;
    page.$('table[name="dieta_table"] tbody tr[name="dieta'+(expectedLenght)+'"]').scrollIntoViewIfNeeded();

    const dieta = page.$('table[name="dieta_table"] tbody tr[name="dieta'+(expectedLenght)+'"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), dieta.click()]);
    sleep(0.5);

    
    let menu = page.$('table[name="menus"] tbody tr[name="menu1"] td[name="anyadirMenu"] button');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), menu.click()]);
    sleep(0.1);
    menu = page.$('table[name="menus"] tbody tr[name="menu1"] td[name="anyadirMenu"] button');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), menu.click()]);
    sleep(0.3);
    const expectedMenusDietaLenght = page.$$('table[name="menusDieta"] tbody tr').length-1;
    const expectedMenusLenght = page.$$('table[name="menus"] tbody tr').length+1;

    let menuDieta = page.$('table[name="menusDieta"] tbody tr[name="menuDieta1"] td[name="eliminarMenu"] button');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), menuDieta.click()]);
    sleep(0.3);

    const actualMenusDietaLenght = page.$$('table[name="menusDieta"] tbody tr').length;
    const actualMenusLenght = page.$$('table[name="menus"] tbody tr').length;

    
    const deleteDietaButton = page.locator('button[name="eliminar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), deleteDietaButton.click()]);
    
    check(page, {
      'MenusDietaLenght': p => actualMenusDietaLenght === expectedMenusDietaLenght,
      'MenusLenght': p=> actualMenusLenght === expectedMenusLenght,
  });

  } finally {
    page.close();
  }
}