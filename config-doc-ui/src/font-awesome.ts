import {findIconDefinition, IconDefinition, IconName, library} from '@fortawesome/fontawesome-svg-core'
import {fas} from '@fortawesome/free-solid-svg-icons'

library.add(fas);

export function fasIcon(name: IconName): IconDefinition {
    const icon: IconDefinition = findIconDefinition({prefix: 'fas', iconName: name})
    if (!icon) {
        console.log("Could not find icon: " + name)
    }
    return icon;
}

