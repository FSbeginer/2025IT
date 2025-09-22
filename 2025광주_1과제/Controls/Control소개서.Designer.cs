namespace _2025광주_1과제.Controls
{
    partial class Control소개서
    {
        /// <summary> 
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region 구성 요소 디자이너에서 생성한 코드

        /// <summary> 
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // label3
            // 
            this.label3.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.label3.Location = new System.Drawing.Point(10, 104);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(178, 44);
            this.label3.TabIndex = 1;
            this.label3.Text = "label2";
            this.label3.MouseDoubleClick += new System.Windows.Forms.MouseEventHandler(this.label3_MouseDoubleClick);
            // 
            // label2
            // 
            this.label2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.label2.Location = new System.Drawing.Point(10, 64);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(178, 40);
            this.label2.TabIndex = 2;
            this.label2.Text = "label2";
            this.label2.MouseDoubleClick += new System.Windows.Forms.MouseEventHandler(this.label3_MouseDoubleClick);
            // 
            // label1
            // 
            this.label1.Dock = System.Windows.Forms.DockStyle.Left;
            this.label1.Location = new System.Drawing.Point(10, 10);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(106, 54);
            this.label1.TabIndex = 3;
            this.label1.Text = "label1";
            this.label1.MouseDoubleClick += new System.Windows.Forms.MouseEventHandler(this.label3_MouseDoubleClick);
            // 
            // label4
            // 
            this.label4.Dock = System.Windows.Forms.DockStyle.Fill;
            this.label4.Location = new System.Drawing.Point(116, 10);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(72, 54);
            this.label4.TabIndex = 4;
            this.label4.Text = "label4";
            this.label4.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.label4.MouseDoubleClick += new System.Windows.Forms.MouseEventHandler(this.label3_MouseDoubleClick);
            // 
            // Control소개서
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label3);
            this.Margin = new System.Windows.Forms.Padding(0);
            this.Name = "Control소개서";
            this.Padding = new System.Windows.Forms.Padding(10);
            this.Size = new System.Drawing.Size(198, 158);
            this.Load += new System.EventHandler(this.Control소개서_Load);
            this.MouseDoubleClick += new System.Windows.Forms.MouseEventHandler(this.label3_MouseDoubleClick);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label4;
    }
}
