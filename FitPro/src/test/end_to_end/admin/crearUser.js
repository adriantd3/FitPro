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

    const beforeLenght = page.$$('#users tbody tr').length-1;

    page.locator('input[name="Nombre"]').type('david');
    page.locator('input[name="Apellidos"]').type('bueno');
    page.locator('input[name="DNI"]').type('12345678G');
    page.locator('input[name="Edad"]').type('21');
    page.locator('input[name="Altura"]').type('100');
    page.locator('input[name="Peso"]').type('100');
    page.locator('input[name="Email"]').type('prueba@gmail.com');
    page.locator('input[name="Contrasenya"]').type('prueba');

    sleep(1);

    const createButton = page.locator('button[name="save-user"]');
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), createButton.click()]);

    const afterLenght = page.$$('#users tbody tr').length-1;

    check(page, {
      'User created': afterLenght == beforeLenght+1,
    });

  } finally {
    page.close();
  }
}