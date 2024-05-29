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

      const CRUDrutinaButton = page.$("section button:first-child");

      sleep(1);
      await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), CRUDrutinaButton.click()]);

      sleep(1);
      page.locator('input[name="nombreRutina"]').clear();
      sleep(1);
      page.locator('input[name="nombreRutina"]').type("Rutina Test");
      sleep(1);

      const crearRutina = page.locator('button[name="crearRutinaButton"]');
      await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), crearRutina.click()]);
      sleep(1);
      
      check(page,{
        "tituloVistaRutina" : (p) => p.locator("h1").textContent() === "Rutina Test"
      })

      const backButton = page.locator("img");
      await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), backButton.click()]);

      check(page,{
        "nuevaRutina" : (p) => p.locator('button[name="Rutina"]').textContent().includes("Rutina Test")
      })

    }catch(error){

    }finally{
        page.close();
    }
}