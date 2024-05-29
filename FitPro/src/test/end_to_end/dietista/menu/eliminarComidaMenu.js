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
    
    
    page.locator('input[name="nombreMenu"]').clear();
    page.locator('input[name="nombreMenu"]').type('MenuTest');
    sleep(0.5);
    
    const saveMenuButton = page.locator('button[name="guardar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), saveMenuButton.click()]);
    
    const expectedLenght = page.$$('table[name="menu_table"] tbody tr').length;
    page.$('table[name="menu_table"] tbody tr[name="menu'+(expectedLenght)+'"]').scrollIntoViewIfNeeded();

    const menu = page.$('table[name="menu_table"] tbody tr[name="menu'+(expectedLenght)+'"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), menu.click()]);
    sleep(0.5);

    
    let comida = page.$('table[name="comidas"] tbody tr[name="comida1"] td[name="anyadirComida"] button');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), comida.click()]);
    sleep(0.1);
    comida = page.$('table[name="comidas"] tbody tr[name="comida1"] td[name="anyadirComida"] button');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), comida.click()]);
    sleep(0.3);
    const expectedComidasMenuLenght = page.$$('table[name="comidasMenu"] tbody tr').length-1;
    const expectedComidasLenght = page.$$('table[name="comidas"] tbody tr').length+1;

    let comidaMenu = page.$('table[name="comidasMenu"] tbody tr[name="comidaMenu1"] td[name="eliminarComida"] button');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), comidaMenu.click()]);
    sleep(0.3);

    const actualComidasMenuLenght = page.$$('table[name="comidasMenu"] tbody tr').length;
    const actualComidasLenght = page.$$('table[name="comidas"] tbody tr').length;

    
    const deleteMenuButton = page.locator('button[name="eliminar"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), deleteMenuButton.click()]);
    
    check(page, {
      'ComidasMenuLenght': p => actualComidasMenuLenght === expectedComidasMenuLenght,
      'ComidasLenght': p=> actualComidasLenght === expectedComidasLenght,
  });

  } finally {
    page.close();
  }
}