import { browser } from "k6/experimental/browser";
import { sleep } from "k6";
import { check } from "k6";

export const options = {
    scenarios: {
        ui: {
            executor: "shared-iterations", // para realizar iteraciones sin indicar el tiempo
            options: {
                browser: {
                    type: "chromium",
                },
            },
        },
    },
    thresholds: {
        checks: ["rate==1.0"],
    },
}

export default async () => {
    const page = browser.newPage();
    try {
      await page.goto('http://localhost:8080/');
      sleep(1);
  
      page.locator('input[name="mail"]').clear();
      page.locator('input[name="password"]').clear();
  
      sleep(1);

      page.locator('input[name="mail"]').type('alvaro@gmail.com');
      page.locator('input[name="password"]').type('alvaro');
      sleep(1);
  
      const loginButton = page.locator('button[name="login"]');
      await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), loginButton.click()]);
  
      check(page, {
        'Title': p => p.locator('h1').textContent() == 'Bienvenido, Alvaro',
      });
      sleep(1);

      const CRUDrutinaButton = page.$("section button:first-child");

      sleep(1);
      await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), CRUDrutinaButton.click()]);

      sleep(1);
      const rutinaButton = page.$("section ul button:first-child");

      sleep(1);
      await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), rutinaButton.click()]);

      sleep(1);

      const sessionButton = page.$("section ul button:first-child");
      if(sessionButton){
        await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), sessionButton.click()]);

        sleep(1);
        const ejercicioNombre = page.$("section a:first-child").textContent();

        const editarSerieClick = page.$("section table tbody a:first-child");

        const serieID = editarSerieClick.getAttribute("id");

        await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), editarSerieClick.click()]);
  
        sleep(1);
  
        page.locator('input[name="peso"]').clear();
        page.locator('input[name="repeticiones"]').clear();
  
        sleep(1);
  
        page.locator('input[name="peso"]').type("45");
        page.locator('input[name="repeticiones"]').type("10");
  
        sleep(1);
  
        const guardarSerie = page.$("input[name='editar-serie']");
        await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), guardarSerie.click()]);
  
        sleep(2);
  
        const idEjercicio = ejercicioNombre.split(" ")[0];
        
        check(page, {
          'Peso': p => p.$('table[id="'+idEjercicio+'"] td[id="peso'+serieID+'"]').textContent() === "45.0",
          'Repeticiones': p => p.$('table[id="'+idEjercicio+'"] td[id="repeticiones'+serieID+'"]').textContent() === "10",
        });
        sleep(1);
      } 
    }finally{
        page.close();
    }
}