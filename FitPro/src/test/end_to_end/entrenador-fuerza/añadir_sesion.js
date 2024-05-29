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
      const rutinaButton = page.$("section ul button:first-child");

      sleep(1);
      await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), rutinaButton.click()]);

      sleep(1);
      page.locator('input[name="nombreSesion"]').clear();
      sleep(1);
      page.locator('input[name="nombreSesion"]').type("Sesion Test");
      sleep(1);

      const crearRutina = page.locator('button[name="crearSesionButton"]');
      await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), crearRutina.click()]);
      sleep(1);
      
      check(page,{
        "tituloVistaSesion" : (p) => p.locator("h1").textContent() === "Sesion Test"
      })

      const backButton = page.locator("img");
      await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), backButton.click()]);

      check(page,{
        "nuevaSesion" : (p) => p.locator('button[name="Sesion"]').textContent().includes("Sesion Test")
      })

    }catch(error){

    }finally{
        page.close();
    }
}