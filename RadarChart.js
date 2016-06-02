import {
  PropTypes,
  requireNativeComponent,
  View
} from 'react-native';

import ChartBase from './ChartBase';
import {yAxisIface} from './YAxisIface';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'RadarChart',
  propTypes: {
    ...ChartBase.propTypes,

    yAxis: PropTypes.shape(yAxisIface),

    skipWebLineCount: PropTypes.number,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        yValues: PropTypes.arrayOf(PropTypes.number),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.lineScatterCandleRadar,
          ...ChartDataSetConfig.lineRadar

        })
      })),
      xValues: PropTypes.arrayOf(PropTypes.string)
    })
  }
};

export default requireNativeComponent('MPAndroidRadarChart', iface);
