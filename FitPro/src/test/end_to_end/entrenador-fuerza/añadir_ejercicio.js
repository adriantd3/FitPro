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

        const anyadirEjercicioButton = page.locator('footer button:last-child');
        await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), anyadirEjercicioButton.click()]);

        sleep(1)

        const ejercicioButton = page.$("button:first-child");
        const ejercicioName = ejercicioButton.textContent().trim();
        
        await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), ejercicioButton.click()]);
        const serieID = page.$("section table tbody a:last-child").getAttribute("id");

        check(page,{
            "filaNueva" : (p) => p.$$("table[id='"+ejercicioName.split(" ")[0]+"'] tr:last-child #peso"+serieID+",#repeticiones"+serieID)
                                .every((td) => td.textContent === "0" || td.textContent === "0.0")
        });
        
      } 
    }finally{
        page.close();
    }
}