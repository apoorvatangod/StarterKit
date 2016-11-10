//main is platform-dependent, it specifies bootstrap of which platform is to be used - see bootstrapping.png
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app.module';

const platform = platformBrowserDynamic();
platform.bootstrapModule(AppModule);