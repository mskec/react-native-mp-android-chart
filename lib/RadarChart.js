import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import PieRadarChartBase from './PieRadarChartBase';
import {yAxisIface} from './AxisIface';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'RadarChart',
  propTypes: {
    ...PieRadarChartBase.propTypes,

    yAxis: PropTypes.shape(yAxisIface),

    drawWeb:PropTypes.bool,
    skipWebLineCount: PropTypes.number,

    data: PropTypes.shape({
      dataSets: PropTypes.arrayOf(PropTypes.shape({
        values: PropTypes.arrayOf(
          PropTypes.oneOfType([
            PropTypes.shape({value: PropTypes.number.isRequired,}),
            PropTypes.number
          ])
        ),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.lineScatterCandleRadar,
          ...ChartDataSetConfig.lineRadar

        })
      })),
      labels: PropTypes.arrayOf(PropTypes.string)
    })
  }
};

export default requireNativeComponent('MPAndroidRadarChart', iface);
